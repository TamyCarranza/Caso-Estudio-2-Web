package com.ejercicio.tamara.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String redirectUrl = "/home";

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String role = auth.getAuthority();

            if ("ROLE_ADMIN".equals(role)) {
                redirectUrl = "/admin/usuarios";
                break;
            } else if ("ROLE_PROFESOR".equals(role)) {
                redirectUrl = "/profesor/reportes";
                break;
            } else if ("ROLE_ESTUDIANTE".equals(role)) {
                redirectUrl = "/estudiante/perfil";
                break;
            }
        }

        response.sendRedirect(redirectUrl);
    }
}
