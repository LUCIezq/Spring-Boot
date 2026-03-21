package com.sastreria.gestiondeprecios.users;

import com.sastreria.gestiondeprecios.users.dto.UserRequest;
import com.sastreria.gestiondeprecios.users.dto.UserResponse;
import com.sastreria.gestiondeprecios.users.mapper.UserMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {

        log.info("Request para crear usuario({})", userRequest.name());
        User saved = userService.create(userMapper.toEntity(userRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailResponse> findUserById(
            @PathVariable
            @Positive(message = "El id debe ser un numero positivo") Long id
    ) {
        log.info("Request para buscar usuario ({})", id);
        User searched = userService.findById(id);
        return ResponseEntity.ok(userMapper.toDetail(searched));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        log.info("Request para obtener todos los usuarios");
        List<User> users = userService.getAll();
        return ResponseEntity.ok(
                userMapper.toDetailAll(users)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Validated @PathVariable Long id) {
        log.info("Request para eliminar usuario ({})", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
