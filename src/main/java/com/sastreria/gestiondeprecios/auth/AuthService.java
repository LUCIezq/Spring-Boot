package com.sastreria.gestiondeprecios.auth;

import com.sastreria.gestiondeprecios.auth.dto.AuthResponse;
import com.sastreria.gestiondeprecios.auth.dto.LoginRequest;
import com.sastreria.gestiondeprecios.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
