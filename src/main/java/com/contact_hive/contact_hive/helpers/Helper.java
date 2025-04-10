package com.contact_hive.contact_hive.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {
            var token = (OAuth2AuthenticationToken) authentication;
            var clientId = token.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User) authentication.getPrincipal();
            if (clientId.equals("google")) {
                return oauth2User.getAttribute("email").toString();
            } else {
                oauth2User.getAttributes().forEach((k, v) -> {
                    System.out.println(k + " : " + v);
                });
                return oauth2User.getAttribute("login").toString();

            }
        } else {
            return authentication.getName();
        }
    }

    public static String getLinkForEmailVerification(String token) {
        return "http://localhost:8081/auth/verify-email?token=" + token;
    }
}
