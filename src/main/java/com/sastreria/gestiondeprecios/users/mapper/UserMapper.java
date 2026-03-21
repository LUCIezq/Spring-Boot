package com.sastreria.gestiondeprecios.users.mapper;

import com.sastreria.gestiondeprecios.users.User;
import com.sastreria.gestiondeprecios.users.UserDetailResponse;
import com.sastreria.gestiondeprecios.users.dto.UserRequest;
import com.sastreria.gestiondeprecios.users.dto.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public User toEntity(UserRequest request) {
        return User.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .build();
    }

    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserDetailResponse toDetail(User user) {
        return UserDetailResponse
                .builder()
                .id(user.getId())
                .nombre(user.getName())
                .build();
    }

    public List<UserResponse> toDetailAll(List<User> users) {
        if (users.isEmpty()) {
            return List.of();
        }
        return users.stream().map(this::toDto).toList();
    }
}
