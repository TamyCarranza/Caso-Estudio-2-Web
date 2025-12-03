package com.ejercicio.tamara.controllers;

import com.ejercicio.tamara.domain.Role;
import com.ejercicio.tamara.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String listarRoles(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "admin/roles";
    }

    @GetMapping("/nuevo")
    public String nuevoRol(Model model) {
        model.addAttribute("rol", new Role());
        return "admin/rol-form";
    }

    @PostMapping("/guardar")
    public String guardarRol(@ModelAttribute("rol") Role rol) {
        roleService.save(rol);
        return "redirect:/admin/roles";
    }

    @GetMapping("/editar/{id}")
    public String editarRol(@PathVariable Long id, Model model) {
        Role rol = roleService.findById(id);
        model.addAttribute("rol", rol);
        return "admin/rol-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarRol(@PathVariable Long id) {
        roleService.deleteById(id);
        return "redirect:/admin/roles";
    }
}
