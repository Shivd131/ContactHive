package com.contact_hive.contact_hive.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

        var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);

        DefaultOAuth2User myOauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);

        if (authorizedClientRegistrationId.equals("google")) {
            String email = myOauthUser.getAttribute("email").toString();
            String name = myOauthUser.getAttribute("name").toString();
            String picture = myOauthUser.getAttribute("picture").toString();
            user.setEmail(email);
            user.setName(name);
            user.setProfilePic(picture);
            user.setPassword("default");
            user.setProviderUserId(user.getName());
            user.setAbout("This account is created using Google.");

        } else if (authorizedClientRegistrationId.equals("github")) {
            String email = myOauthUser.getAttribute("email") != null
                    ? myOauthUser.getAttribute("email").toString()
                    : myOauthUser.getAttribute("login").toString();
            String picture = myOauthUser.getAttribute("avatar_url") != null
                    ? myOauthUser.getAttribute("avatar_url").toString()
                    : "";
            String name = myOauthUser.getAttribute("login") != null
                    ? myOauthUser.getAttribute("login").toString()
                    : "";
            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(myOauthUser.getName());
            user.setAbout("This account is created using Github.");

        }
        // GETTING THE AUTHENTICATION OBJECT TO FETCH THE SIGNIN USER DETAILS. A
        // PRINCIPAL OBJECT IS RETURNED, IT IS THE ENTITY WHICH IS BEGIN AUTHENTICATED
        // OR AUTHORIZED.

        // save details to db

        User user2 = userRepository.findByEmail(user.getEmail()).orElseGet(() -> {
            userRepository.save(user);
            logger.info("User saved" + user.getEmail());
            return user;
        });
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
