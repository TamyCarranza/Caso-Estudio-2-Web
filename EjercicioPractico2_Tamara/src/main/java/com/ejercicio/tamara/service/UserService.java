package com.ejercicio.tamara.service;

import com.ejercicio.tamara.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);

    User save(User user);

    void deleteById(Long id);

    List<User> findByRolNombre(String rolNombre);

    List<User> findByFechaCreacionBetween(LocalDateTime desde, LocalDateTime hasta);

    List<User> findByTexto(String texto);
}
