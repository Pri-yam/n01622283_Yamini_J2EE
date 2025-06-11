package com.example.lab6.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class GreetingService {
    public String sayHello() {
        return "Hello, Spring Beans!";
    }

    @PostConstruct
    public void init() {
        System.out.println("GreetingService initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("GreetingService destroyed");
    }
}
