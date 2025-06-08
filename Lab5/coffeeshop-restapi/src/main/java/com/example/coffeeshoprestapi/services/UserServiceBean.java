package com.example.coffeeshoprestapi.services;

import jakarta.ejb.Stateless;
import java.util.Map;

@Stateless
public class UserServiceBean {
    private static final Map<String, String> userPasswords = Map.of(
            "admin", "adminpass",
            "user", "userpass"
    );

    private static final Map<String, String> userRoles = Map.of(
            "admin", "admin",
            "user", "user"
    );

    public boolean isValidUser(String username, String password) {
        return userPasswords.containsKey(username) && userPasswords.get(username).equals(password);
    }

    public String getUserRole(String username) {
        return userRoles.get(username);
    }
}

