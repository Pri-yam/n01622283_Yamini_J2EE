package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.User;
import com.example.coffeeshop.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index"; // Default login page
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user"; // Registration form
    }

    @PostMapping("/signup")
    public String addNewUser(@ModelAttribute User user, Model model) {
        logger.info("Received request to register user: {}", user.getUserName());

        // ✅ Encode password before saving
        user.setPassword(userService.encodePassword(user.getPassword()));

        userService.addUser(user);
        model.addAttribute("response", "added"); // shows "Please login now" on index page
        return "index";
    }
}
