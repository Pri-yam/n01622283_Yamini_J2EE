package com.example.coffeeshop.services;

import com.example.coffeeshop.models.User;
import com.example.coffeeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
