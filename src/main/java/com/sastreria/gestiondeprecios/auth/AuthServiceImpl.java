package com.sastreria.gestiondeprecios.auth;

import com.sastreria.gestiondeprecios.auth.dto.AuthResponse;
import com.sastreria.gestiondeprecios.auth.dto.LoginRequest;
import com.sastreria.gestiondeprecios.auth.dto.RegisterRequest;
import com.sastreria.gestiondeprecios.enums.Rol;
import com.sastreria.gestiondeprecios.exceptions.auth.DuplicateEmailException;
import com.sastreria.gestiondeprecios.jwt.JwtService;
import com.sastreria.gestiondeprecios.users.User;
import com.sastreria.gestiondeprecios.users.UserRepository;
import com.sastreria.gestiondeprecios.users.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsUserByEmail(request.email())) {
            throw new DuplicateEmailException("El email ya esta registrado");
        }

        User user = User.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Rol.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                "Bearer",
                UserResponse.builder()
                        .id(user.getId())
                        .createdAt(user.getCreatedAt())
                        .rol(user.getRole())
                        .build()
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                "Bearer",
                UserResponse.builder()
                        .id(user.getId())
                        .createdAt(user.getCreatedAt())
                        .rol(user.getRole())
                        .build()
        );
    }
}
