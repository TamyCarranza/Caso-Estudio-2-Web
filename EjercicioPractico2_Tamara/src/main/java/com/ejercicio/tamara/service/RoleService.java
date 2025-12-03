package com.ejercicio.tamara.service;

import com.ejercicio.tamara.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role save(Role role);

    void deleteById(Long id);
}
