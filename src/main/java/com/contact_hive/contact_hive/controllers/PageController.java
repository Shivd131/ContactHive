package com.contact_hive.contact_hive.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("name", "This is home");
        return "home";
    }

    // same stuff for about page
    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("name", "This is about");
        return "about";
    }

    // same stuff for services page
    @GetMapping("/services")
    public String servicesPage(Model model) {
        model.addAttribute("name", "This is services");
        return "services";
    }
}
