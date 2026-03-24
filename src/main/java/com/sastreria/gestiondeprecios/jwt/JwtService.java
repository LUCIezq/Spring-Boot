package com.sastreria.gestiondeprecios.jwt;

public interface JwtService {
    String generateToken(String username);

    String extractUsername(String token);

    boolean validateToken(String token);
}
