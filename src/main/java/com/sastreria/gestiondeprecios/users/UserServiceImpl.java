package com.sastreria.gestiondeprecios.users;

import com.sastreria.gestiondeprecios.exceptions.user.UserNotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User create(User entity) {
        if (existByEmail(entity.getEmail())) {
            throw new UserNotFound("El usuario ya existe");
        }
        return userRepository.save(entity);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFound("Usuario no encontrado")
        );
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFound("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }
}
