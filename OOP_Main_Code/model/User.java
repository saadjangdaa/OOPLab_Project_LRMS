// User.java
package com.laptop.rental.model;

public class User {
    private String username;
    private String password;
    private int role; // 0 for admin, 1 for student

    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public int getRole() { return role; }
    public void setRole(int role) { this.role = role; }

    public boolean isAdmin() {
        return role == 0;
    }

    public boolean isStudent() {
        return role == 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role=" + (role == 0 ? "Admin" : "Student") +
                '}';
    }
}