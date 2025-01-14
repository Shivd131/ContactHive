package com.contact_hive.contact_hive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.contact_hive.contact_hive.entities.User;
import com.contact_hive.contact_hive.forms.UserForm;
import com.contact_hive.contact_hive.helpers.Message;
import com.contact_hive.contact_hive.helpers.MessageType;
import com.contact_hive.contact_hive.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
    public String registerPagePost(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,
            HttpSession session) {
        System.out.println("Register page post method called");

        // Validate form data
        if (bindingResult.hasErrors()) {
            System.out.println("Binding result has errors: " + bindingResult.getAllErrors());
            session.setAttribute("message", Message.builder()
                    .content("Invalid form data! Please check the inputs.")
                    .type(MessageType.red)
                    .build());
            return "register"; // Return to the same page with error
        }

        // Check if user email already exists
        if (userService.isUserExistByEmail(userForm.getEmail())) {
            session.setAttribute("message", Message.builder()
                    .content("A user with this email already exists! Please use a different email.")
                    .type(MessageType.red)
                    .build());
            return "register"; // Redirect to the register page with error
        }

        try {
            // Map UserForm to User entity
            User user = new User();
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setAbout(userForm.getAbout());
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setProfilePic("https://upload.wikimedia.org/wikipedia/commons/1/1e/Default-avatar.jpg");
            user.setEnabled(false);

            // Save user
            User savedUser = userService.saveUser(user);
            System.out.println("User saved: " + savedUser);

            // Set success message
            session.setAttribute("message", Message.builder()
                    .content("Registration successful! Please check your email for verification.")
                    .type(MessageType.green)
                    .build());

            // Redirect to login or another page
            return "redirect:/login";

        } catch (Exception e) {
            // Log and handle any unexpected exceptions
            System.err.println("Error during registration: " + e.getMessage());
            session.setAttribute("message", Message.builder()
                    .content("An unexpected error occurred. Please try again later.")
                    .type(MessageType.red)
                    .build());
            return "register"; // Return to register page with error
        }
    }

}
