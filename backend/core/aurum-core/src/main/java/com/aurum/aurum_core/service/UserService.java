package com.aurum.aurum_core.service;

import com.aurum.aurum_core.model.User;
import com.aurum.aurum_core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Considerar encriptación
        user.setLoggedIn(false);
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }
        user.setLoggedIn(true);
        return userRepository.save(user);
    }

    public void logout(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
        User user = optionalUser.get();
        user.setLoggedIn(false);
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
        userRepository.delete(optionalUser.get());
    }
}
