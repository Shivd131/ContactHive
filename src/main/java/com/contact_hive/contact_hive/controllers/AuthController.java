// package com.contact_hive.contact_hive.controllers;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;

// @Controller
// public class AuthController {

//     @GetMapping("/login")
//     public String showLoginPage() {
//         return "login"; // This should match your login template name
//     }

//     @PostMapping("/authenticate")
//     public String processLogin() {
//         // Spring Security will handle the authentication
//         return "redirect:/user/dashboard";
//     }
// }