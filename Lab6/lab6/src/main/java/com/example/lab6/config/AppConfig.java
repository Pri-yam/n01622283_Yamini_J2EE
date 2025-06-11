package com.example.lab6.config;

import com.example.lab6.service.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public GreetingService greetingService() {
        return new GreetingService();
    }
}
