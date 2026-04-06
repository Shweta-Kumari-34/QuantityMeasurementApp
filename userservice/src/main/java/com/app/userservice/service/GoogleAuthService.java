package com.app.userservice.service;
import com.app.userservice.dto.AuthResponseDTO;

public interface GoogleAuthService {
    AuthResponseDTO authenticateWithGoogle(String idToken);
}