package com.contact_hive.contact_hive.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard page
    @GetMapping("/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }
    
    //user add contacts page
    @RequestMapping("/add-contacts")
    public String addContacts() {
        return "user/add-contacts";
    }

    //user view contacts page
    @RequestMapping("/view-contacts")
    public String viewContacts() {
        return "user/view-contacts";
    }

    //user edit contact page
    @RequestMapping("/edit-contact")
    public String editContact() {
        return "user/edit-contact";
    }

    //user delete contact page
    @RequestMapping("/delete-contact")
    public String deleteContact() {
        return "user/delete-contact";
    }
}
