package com.sastreria.gestiondeprecios.users;

import com.sastreria.gestiondeprecios.exceptions.user.UserNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFound("Usuario no encontrado")
        );
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
