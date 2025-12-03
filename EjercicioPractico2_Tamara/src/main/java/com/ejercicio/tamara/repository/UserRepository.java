package com.ejercicio.tamara.repository;

import com.ejercicio.tamara.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByRol_Nombre(String nombreRol);

    List<User> findByFechaCreacionBetween(LocalDateTime desde, LocalDateTime hasta);

    List<User> findByEmailContainingIgnoreCaseOrNombreContainingIgnoreCase(String emailParte, String nombreParte);
}
