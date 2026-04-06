package com.app.userservice.security;
import jakarta.servlet.*;


import jakarta.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
 * =========================================================
 * JWT Filter
 * =========================================================
 *
 * Purpose:
 * This filter runs on EVERY request.
 *
 * It:
 * - Extracts JWT token from request
 * - Validates the token
 * - Sets authentication in Spring Security context
 *
 * Without this:
 *  JWT token will never be checked
 *  All secured APIs will fail
 *  
 *  
 *  Request comes with JWT
 *       |
 *   JwtFilter intercepts
 *       |
 *   Extract token
 *       |
 *   Extract username
 *       |
 *   Load user from DB
 *       |
 *   Validate token
 *       |
 *   Create Authentication object
 *       |
 *   Set in SecurityContext
 *       |
 *   Forward request
 *       |
 *   Controller executes (user is authenticated)
 */

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain chain)
            throws ServletException, IOException {

        /*
         * Skip JWT check for auth APIs
         */
        String path = request.getServletPath();

        if (path.startsWith("/auth/")) {
            chain.doFilter(request, response);
            return;
        }

        /*
         * Step 1: Get Authorization header
         *
         * Example:
         * Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
         */
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        /*
         * Step 2: Extract token
         */
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            username = jwtUtil.extractUsername(token);
        }

        /*
         * Step 3: If username exists AND user is not already authenticated
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            /*
             * Step 4: Validate token
             */
            if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                /*
                 * Step 5: Create authentication object
                 */
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                /*
                 * Attach request details (IP, session, etc.)
                 */
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                /*
                 * Step 6: Set authentication in Security Context
                 */
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        /*
         * Step 7: Continue request flow
         */
        chain.doFilter(request, response);
    }
}