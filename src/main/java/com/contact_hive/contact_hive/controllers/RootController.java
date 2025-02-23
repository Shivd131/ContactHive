package com.contact_hive.contact_hive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.contact_hive.contact_hive.entities.User;
import com.contact_hive.contact_hive.helpers.Helper;
import com.contact_hive.contact_hive.services.UserService;

//root means this method will be called for every request

@ControllerAdvice
// Specialization of @Component for classes that declare @ExceptionHandler,
// @InitBinder, or @ModelAttribute methods to be shared across multiple
// @Controller classes
public class RootController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserToModel(Model model, Authentication authentication) {
        if (authentication == null) {
            System.out.println("Authentication object is null. User is not logged in.");
            return;
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);
        System.out.println("Retrieved username from authentication: " + username);

        User user = userService.getUserByEmail(username);
        if (user == null) {
            System.out.println("No user found with email: " + username);
            model.addAttribute("loggedInUser", null);
            return;
        }

        System.out.println("Logged-in user email: " + user.getEmail());
        model.addAttribute("loggedInUser", user);
    }

}
