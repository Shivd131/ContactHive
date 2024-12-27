package com.contact_hive.contact_hive.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserToModel(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }
        System.out.println("Inside addLoggedInUserToModel");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);
    }

}
