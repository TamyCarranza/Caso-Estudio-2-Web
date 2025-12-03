package com.ejercicio.tamara.controllers;

import com.ejercicio.tamara.domain.User;
import com.ejercicio.tamara.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ConsultasController {

    private final UserService userService;

    public ConsultasController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/consultas")
    public String consultas(
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String desde,
            @RequestParam(required = false) String hasta,
            @RequestParam(required = false) String texto,
            Model model) {

        List<User> porRol = null;
        if (rol != null && !rol.isBlank()) {
            porRol = userService.findByRolNombre(rol);
        }

        List<User> porFechas = null;
        if (desde != null && hasta != null && !desde.isBlank() && !hasta.isBlank()) {
            try {
                LocalDate d1 = LocalDate.parse(desde);
                LocalDate d2 = LocalDate.parse(hasta);
                LocalDateTime inicio = d1.atStartOfDay();
                LocalDateTime fin = d2.atTime(23, 59, 59);
                porFechas = userService.findByFechaCreacionBetween(inicio, fin);
            } catch (Exception e) {
            }
        }

        List<User> porTexto = null;
        if (texto != null && !texto.isBlank()) {
            porTexto = userService.findByTexto(texto);
        }

        model.addAttribute("porRol", porRol);
        model.addAttribute("porFechas", porFechas);
        model.addAttribute("porTexto", porTexto);

        return "consultas/consultas";
    }
}
