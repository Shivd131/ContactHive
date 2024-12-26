package com.contact_hive.contact_hive.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.contact_hive.contact_hive.implementation.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails user = User.
    // withDefaultPasswordEncoder().
    // username("admin123").
    // password("admin123").
    // roles("ADMIN", "USER").
    // build();

    // var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
    // return InMemoryUserDetailsManager;
    // }

    // user create and login using database
    // user details will be fetched from the Userdetailservice
    // the authentiation will occur using the authentication provider

    @Autowired
    private SecurityCustomUserDetailService securityCustomUserDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll();
                    authorize.requestMatchers("/", "/home", "/login", "/register").permitAll();
                    authorize.requestMatchers("/user/**").authenticated();
                    authorize.anyRequest().permitAll();
                })
                .formLogin(formLogin -> {
                    formLogin.loginPage("/login")
                            .loginProcessingUrl("/authenticate")
                            .defaultSuccessUrl("/user/dashboard", true)
                            // .failureUrl("/login?error=true")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .permitAll();
                });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/logout")
                    .logoutSuccessUrl("/login");
            // .invalidateHttpSession(true)
            // .deleteCookies("JSESSIONID");
        });

        // oauth configs
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
