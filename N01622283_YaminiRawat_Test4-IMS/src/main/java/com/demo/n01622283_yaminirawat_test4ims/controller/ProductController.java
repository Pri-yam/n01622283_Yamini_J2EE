package com.demo.n01622283_yaminirawat_test4ims.controller;

import com.demo.n01622283_yaminirawat_test4ims.model.Product;
import com.demo.n01622283_yaminirawat_test4ims.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/products";
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products"; // products.html
    }

    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product"; // add-product.html
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Product product = productService.getAllProducts()
                .stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("product", product);
        return "edit-product"; // edit-product.html
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/update-stock/{id}")
    public String showStockForm(@PathVariable Long id, Model model) {
        Product product = productService.getAllProducts()
                .stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("product", product);
        return "update-stock"; // update-stock.html
    }

    @PostMapping("/update-stock/{id}")
    public String updateStock(@PathVariable Long id, @RequestParam int stock) {
        productService.updateStock(id, stock);
        return "redirect:/products";
    }
}
