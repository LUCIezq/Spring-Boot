package com.sastreria.gestiondeprecios.users;

import com.sastreria.gestiondeprecios.users.dto.UserRequest;
import com.sastreria.gestiondeprecios.users.dto.UserResponse;

public interface UserService {
    User saveUser(User entity);

    User findUserById(Long id);
}
