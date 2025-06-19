package com.laptop.rental.model;

public class Laptop {
    private int id;
    private String brand;
    private String model;
    private String specifications;
    private boolean available;
    private double hourlyRate;
    private String condition; // New, Good, Fair, Poor

    public Laptop(int id, String brand, boolean available) {
        this.id = id;
        this.brand = brand;
        this.available = available;
        this.hourlyRate = 200.0; // Default rate
        this.condition = "Good";
    }

    public Laptop(int id, String brand, String model, String specifications, boolean available, double hourlyRate, String condition) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.specifications = specifications;
        this.available = available;
        this.hourlyRate = hourlyRate;
        this.condition = condition;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public String getSpecifications() { return specifications; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    
    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
    
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", available=" + available +
                ", hourlyRate=" + hourlyRate +
                ", condition='" + condition + '\'' +
                '}';
    }
}