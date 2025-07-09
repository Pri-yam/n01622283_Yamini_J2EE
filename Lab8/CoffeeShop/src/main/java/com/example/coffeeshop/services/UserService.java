package com.example.coffeeshop.services;

import com.example.coffeeshop.models.User;
import com.example.coffeeshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        logger.info("Adding a new user: {}", user.getUserName());
        userRepository.save(user);
    }

    public User userLogin(String userName, String password) {
        logger.info("Fetching user by name from the database");
        List<User> userList = userRepository.findByUserName(userName);

        return userList.stream().filter(user -> user.getPassword().equals(password)).findFirst().orElse(null);
    }
}
