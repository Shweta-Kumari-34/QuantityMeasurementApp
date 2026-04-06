package com.app.userservice.service;
import com.app.userservice.dto.AuthRequestDTO;
import com.app.userservice.dto.AuthResponseDTO;

public interface AuthService {

    String register(AuthRequestDTO request);

    AuthResponseDTO login(AuthRequestDTO request);
}