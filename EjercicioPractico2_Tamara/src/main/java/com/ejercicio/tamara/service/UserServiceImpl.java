package com.ejercicio.tamara.service;

import com.ejercicio.tamara.domain.User;
import com.ejercicio.tamara.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        if (user.getFechaCreacion() == null) {
            user.setFechaCreacion(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByRolNombre(String rolNombre) {
        return userRepository.findByRol_Nombre(rolNombre);
    }

    @Override
    public List<User> findByFechaCreacionBetween(LocalDateTime desde, LocalDateTime hasta) {
        return userRepository.findByFechaCreacionBetween(desde, hasta);
    }

    @Override
    public List<User> findByTexto(String texto) {
        if (texto == null) {
            texto = "";
        }
        return userRepository.findByEmailContainingIgnoreCaseOrNombreContainingIgnoreCase(texto, texto);
    }
}
