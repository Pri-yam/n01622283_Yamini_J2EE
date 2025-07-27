package com.example.coffeeshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "index";  // ✅ Still returns index.html
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("message", "Login successful!");
        return "home";
    }
}

