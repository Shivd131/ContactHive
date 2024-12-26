package com.contact_hive.contact_hive.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.contact_hive.contact_hive.entities.Providers;
import com.contact_hive.contact_hive.entities.User;
import com.contact_hive.contact_hive.helpers.AppConstants;
import com.contact_hive.contact_hive.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.info("OAuthAuthenticationSuccessHandler.onAuthenticationSuccess()");

        // GETTING THE AUTHENTICATION OBJECT TO FETCH THE SIGNIN USER DETAILS. A
        // PRINCIPAL OBJECT IS RETURNED, IT IS THE ENTITY WHICH IS BEGIN AUTHENTICATED
        // OR AUTHORIZED.

        DefaultOAuth2User myOauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        // save details to db
        String email = myOauthUser.getAttribute("email").toString();
        String name = myOauthUser.getAttribute("name").toString();
        String picture = myOauthUser.getAttribute("picture").toString();

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setProfilePic(picture);
        user.setPassword("default");
        user.setUserId(UUID.randomUUID().toString());
        user.setProvider(Providers.GOOGLE);
        user.setEnabled(true);

        user.setEmailVerified(true);
        user.setProviderUserId(user.getName());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setAbout("This account is created using Google.");

        User user2 = userRepository.findByEmail(email).orElseGet(() -> {
            userRepository.save(user);
            logger.info("User saved" + email);
            return user;
        });
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
