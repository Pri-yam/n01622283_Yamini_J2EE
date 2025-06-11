package com.example.lab6;

import com.example.lab6.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingPrinter {
    private final GreetingService greetingService;

    @Autowired
    public GreetingPrinter(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void printMessage() {
        System.out.println(greetingService.sayHello());
    }
}
