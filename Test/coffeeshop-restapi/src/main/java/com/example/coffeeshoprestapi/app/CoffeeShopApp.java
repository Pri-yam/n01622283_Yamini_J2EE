package com.example.coffeeshoprestapi.app;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class CoffeeShopApp extends Application {
    // Scans for @Path-annotated classes in the package
}
