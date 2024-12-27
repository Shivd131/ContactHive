package com.contact_hive.contact_hive.controllers;

// import org.hibernate.validator.internal.util.logging.LoggerFactory; // Removed duplicate import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact_hive.contact_hive.entities.User;
import com.contact_hive.contact_hive.helpers.Helper;
import com.contact_hive.contact_hive.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    // user dashboard page
    @GetMapping("/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String profile() {

        return "user/profile";
    }

    // user add contacts page
    @RequestMapping("/add-contacts")
    public String addContacts() {
        return "user/add-contacts";
    }

    // user view contacts page
    @RequestMapping("/view-contacts")
    public String viewContacts() {
        return "user/view-contacts";
    }

    // user edit contact page
    @RequestMapping("/edit-contact")
    public String editContact() {
        return "user/edit-contact";
    }

    // user delete contact page
    @RequestMapping("/delete-contact")
    public String deleteContact() {
        return "user/delete-contact";
    }
}
