package com.sastreria.gestiondeprecios.users;

import java.util.List;

public interface UserService {
    User create(User entity);

    boolean existByEmail(String email);

    User findById(Long id);

    List<User> getAll();

    void delete(Long id);
}
