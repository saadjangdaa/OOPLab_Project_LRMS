package com.laptop.rental.model;

public class Admin extends User {
    private String adminId;
    private String email;

    public Admin(String username, String password, String adminId, String email) {
        super(username, password, 0); // 0 for admin role
        this.adminId = adminId;
        this.email = email;
    }

    // Getters and setters
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", adminId='" + adminId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
} 