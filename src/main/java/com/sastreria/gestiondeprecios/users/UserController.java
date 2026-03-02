package com.sastreria.gestiondeprecios.users;

import com.sastreria.gestiondeprecios.users.dto.UserRequest;
import com.sastreria.gestiondeprecios.users.dto.UserResponse;
import com.sastreria.gestiondeprecios.users.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {

        log.info("Request para crear usuario({})", userRequest.nombre());

        User saved = userService.saveUser(userMapper.toEntity(userRequest));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailResponse> findUserById(@PathVariable Long id) {
        log.info("Request para buscar usuario ({})", id);

        User searched = userService.findUserById(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userMapper.toDetail(searched));
    }

}
