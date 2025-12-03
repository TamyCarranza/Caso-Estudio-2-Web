package com.ejercicio.tamara.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

    @GetMapping("/reportes")
    public String reportes() {
        return "profesor/reportes";
    }
}
