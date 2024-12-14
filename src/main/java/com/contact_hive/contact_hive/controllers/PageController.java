package com.contact_hive.contact_hive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.contact_hive.contact_hive.entities.User;
import com.contact_hive.contact_hive.forms.UserForm;
import com.contact_hive.contact_hive.services.UserService;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

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

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("name", "This is contact");
        return "contact";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("name", "This is login");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePost(@ModelAttribute UserForm userForm, Model model) {
        System.out.println("Register page post method called");
        // fetch form data
        System.out.println(userForm);

        // Add attributes to the model if needed (e.g., success message)
        model.addAttribute("success", "Registration successful!");

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://upload.wikimedia.org/wikipedia/commons/1/1e/Default-avatar.jpg");

        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);
        // Render the register page again
        return "register";
    }

}
