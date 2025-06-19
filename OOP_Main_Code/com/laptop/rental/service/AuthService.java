// AuthService.java
package com.laptop.rental.service;

import com.laptop.rental.model.User;
import com.laptop.rental.repository.UserRepository;
import java.io.IOException;

public class AuthService {
    private UserRepository userRepository = new UserRepository();

    public User login(String username, String password) throws IOException {
        for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void signup(String username, String password, int role) throws IOException {
        userRepository.save(new User(username, password, role));
    }
}