package com.ejercicio.tamara.controllers;

import com.ejercicio.tamara.domain.User;
import com.ejercicio.tamara.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    private final UserService userService;

    public EstudianteController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/perfil")
    public String perfil(Authentication auth, Model model) {
        String email = auth.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("usuario", user);
        return "estudiante/perfil";
    }
}
