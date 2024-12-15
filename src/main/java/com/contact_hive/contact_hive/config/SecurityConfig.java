package com.contact_hive.contact_hive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    // user create and login using in-memory service
    // spring security uses userDetailsService to authenticate user. it fetches user
    // detils from database using that service. Our user is in-memory so we need to
    // create a bean of userDetailsService
    // we will create a bean of userDetailsService and return an object of
    // InMemoryUserDetailsManager
    // InMemoryUserDetailsManager is a class that implements UserDetailsService
    // interface
    // InMemoryUserDetailsManager has a method createUser that creates a user
    // we will create a user using createUser method and return it
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.
        withDefaultPasswordEncoder().
        username("admin123").
        password("admin123").
        roles("ADMIN", "USER").
        build();

        var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
        return InMemoryUserDetailsManager;
    }
}
