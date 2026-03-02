package com.sastreria.gestiondeprecios.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return null;
    }

}
