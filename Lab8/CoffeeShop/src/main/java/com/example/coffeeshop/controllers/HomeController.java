package com.example.coffeeshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome(Model model)
    {
        model.addAttribute("message", "This is the debut homepage for our Java Beans & Bytes coffee shop, crafted to share our passion for coffee and create a welcoming experience for all.");
        return "index";
    }
}
