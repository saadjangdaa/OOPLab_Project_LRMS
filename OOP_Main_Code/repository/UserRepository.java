// UserRepository.java
package com.laptop.rental.repository;

import com.laptop.rental.model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String FILE_NAME = "users.txt";

    public void save(User user) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.printf("Username: %s Password: %s Role: %d%n", 
                    user.getUsername(), user.getPassword(), user.getRole());
        }
    }

    public List<User> findAll() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 6) {
                    String username = parts[1];
                    String password = parts[3];
                    int role = Integer.parseInt(parts[5]);
                    users.add(new User(username, password, role));
                }
            }
        }
        return users;
    }
}

// Similar repository classes would be created for Student, Laptop, and Booking