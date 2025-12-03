package com.ejercicio.tamara.controllers;

import com.ejercicio.tamara.domain.Role;
import com.ejercicio.tamara.domain.User;
import com.ejercicio.tamara.service.RoleService;
import com.ejercicio.tamara.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService,
                          RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        List<User> usuarios = userService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "admin/usuarios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        User user = new User();
        List<Role> roles = roleService.findAll();

        model.addAttribute("usuario", user);
        model.addAttribute("roles", roles);

        return "admin/usuario-form";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") User user,
                                 @RequestParam("rolId") Long rolId) {

        Role rol = roleService.findById(rolId);
        user.setRol(rol);

        if (user.getFechaCreacion() == null) {
            user.setFechaCreacion(LocalDateTime.now());
        }

        userService.save(user);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        List<Role> roles = roleService.findAll();

        model.addAttribute("usuario", user);
        model.addAttribute("roles", roles);

        return "admin/usuario-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}")
    public String detalleUsuario(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("usuario", user);
        return "admin/usuario-detalle";
    }
}
