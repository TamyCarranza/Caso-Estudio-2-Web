package com.ejercicio.tamara.controllers;

import com.ejercicio.tamara.domain.User;
import com.ejercicio.tamara.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Authentication auth, Model model) {

        if (auth != null) {
            String email = auth.getName(); // email del usuario logueado
            User user = userService.findByEmail(email);

            model.addAttribute("usuario", user);
            model.addAttribute("rol", "ROLE_" + user.getRol().getNombre());
        }

        return "home";
    }
}
