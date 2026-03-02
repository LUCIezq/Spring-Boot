package com.sastreria.gestiondeprecios.users.mapper;

import com.sastreria.gestiondeprecios.users.User;
import com.sastreria.gestiondeprecios.users.UserDetailResponse;
import com.sastreria.gestiondeprecios.users.dto.UserRequest;
import com.sastreria.gestiondeprecios.users.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequest request) {
        return User.builder()
                .nombre(request.nombre())
                .build();
    }

    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nombre(user.getNombre())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserDetailResponse toDetail(User user) {
        return UserDetailResponse
                .builder()
                .id(user.getId())
                .nombre(user.getNombre())
                .build();
    }
}
