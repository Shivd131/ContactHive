package com.contact_hive.contact_hive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

import com.contact_hive.contact_hive.entities.User;
import com.contact_hive.contact_hive.repositories.UserRepository;
import com.contact_hive.contact_hive.services.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/verify-email")
    public String verifyEmail(
            @RequestParam("token") String token) {

        User user = userService.getUserByToken(token).orElse(null);

        if (user != null) {
            if (user.getEmailToken().equals(token)) {

                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);
                return "success_page";
            }
        }

        return "error_page";
    }
}