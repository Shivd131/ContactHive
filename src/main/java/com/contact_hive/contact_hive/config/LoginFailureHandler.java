package com.contact_hive.contact_hive.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.contact_hive.contact_hive.helpers.Message;
import com.contact_hive.contact_hive.helpers.MessageType;

import org.springframework.security.authentication.DisabledException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof DisabledException) {
            HttpSession session = request.getSession();
            session.setAttribute("message", Message.builder()
                    .content("Your account is disabled, verification link is sent to your email.").type(MessageType.red).build());
            response.sendRedirect("/login");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("message",
                    Message.builder().content("Invalid username or password").type(MessageType.red)
                            .build());
            response.sendRedirect("/login");
        }
    }

}
