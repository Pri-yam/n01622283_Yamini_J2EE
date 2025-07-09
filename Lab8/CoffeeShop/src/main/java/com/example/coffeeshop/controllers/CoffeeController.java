package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.Coffee;
import com.example.coffeeshop.services.CoffeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    // DISPLAY ALL COFFEES (LIST PAGE)
    @GetMapping
    public String getAllCoffees(Model model) {
        model.addAttribute("coffees", coffeeService.getAllCoffees());
        return "coffee-list";  // Return view (coffee-list.html)
    }

    // DISPLAY FORM TO ADD A NEW COFFEE
    @GetMapping("/add")
    public String showAddCoffeeForm(Model model) {
        model.addAttribute("coffee", new Coffee());
        return "coffee-add";  // Return view (coffee-add.html)
    }

    // ADD A NEW COFFEE (FORM SUBMISSION)
    @PostMapping("/add")
    public String addCoffee(@ModelAttribute Coffee coffee) {
        coffeeService.addCoffee(coffee);
        return "redirect:/coffees";  // Redirect to list after adding
    }

    // VIEW DETAILS OF A COFFEE
    @GetMapping("/{id}")
    public String getCoffeeDetails(@PathVariable Long id, Model model) {
        coffeeService.getCoffeeById(id).ifPresent(coffee -> model.addAttribute("coffee", coffee));
        return "coffee-details";  // Return view (coffee-details.html)
    }

    // SHOW UPDATE FORM
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Coffee> coffeeOptional = coffeeService.getCoffeeById(id);
        if (coffeeOptional.isPresent()) {
            model.addAttribute("coffee", coffeeOptional.get());
            return "coffee-edit";  // Return edit view
        } else {
            return "redirect:/coffees";  // Redirect if coffee not found
        }
    }

    // HANDLE UPDATE FORM SUBMISSION USING PUT
    @PutMapping("/update/{id}")
    public String updateCoffee(@PathVariable Long id, @ModelAttribute Coffee coffee) {
        coffeeService.updateCoffee(id, coffee);
        return "redirect:/coffees";  // Redirect to the coffee list after updating
    }

    // DELETE A COFFEE ITEM
    @GetMapping("/delete/{id}")
    public String deleteCoffee(@PathVariable Long id) {
        coffeeService.deleteCoffee(id);
        return "redirect:/coffees";  // Redirect to list after deletion
    }
}
