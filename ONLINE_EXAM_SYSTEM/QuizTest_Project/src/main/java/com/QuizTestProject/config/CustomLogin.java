package com.QuizTestProject.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogin implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "";

             if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("STUDENT"))) {
            redirectUrl = "/quiz";
        } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("TEACHER"))) {
            redirectUrl = "/questions";
        }

        response.sendRedirect(redirectUrl);
    }
}
