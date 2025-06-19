// Booking.java
package com.laptop.rental.model;

import java.util.Date;

public class Booking {
    private int id;
    private int studentId;
    private int laptopId;
    private Date bookingDate;
    private Date returnDate;
    private int durationHours;
    private double chargePerHour;
    private double totalCharge;
    private boolean active;
    private String status; // Active, Returned, Overdue

    public Booking(int studentId, int laptopId, int durationHours, double chargePerHour) {
        this.studentId = studentId;
        this.laptopId = laptopId;
        this.bookingDate = new Date();
        this.durationHours = durationHours;
        this.chargePerHour = chargePerHour;
        this.totalCharge = calculateTotalCharge();
        this.active = true;
        this.status = "Active";
    }

    public Booking(int id, int studentId, int laptopId, Date bookingDate, Date returnDate, 
                   int durationHours, double chargePerHour, boolean active, String status) {
        this.id = id;
        this.studentId = studentId;
        this.laptopId = laptopId;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
        this.durationHours = durationHours;
        this.chargePerHour = chargePerHour;
        this.totalCharge = calculateTotalCharge();
        this.active = active;
        this.status = status;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    
    public int getLaptopId() { return laptopId; }
    public void setLaptopId(int laptopId) { this.laptopId = laptopId; }
    
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    
    public int getDurationHours() { return durationHours; }
    public void setDurationHours(int durationHours) { 
        this.durationHours = durationHours; 
        this.totalCharge = calculateTotalCharge();
    }
    
    public double getChargePerHour() { return chargePerHour; }
    public void setChargePerHour(double chargePerHour) { 
        this.chargePerHour = chargePerHour; 
        this.totalCharge = calculateTotalCharge();
    }
    
    public double getTotalCharge() { return totalCharge; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double calculateTotalCharge() {
        return durationHours * chargePerHour;
    }

    public void markAsReturned() {
        this.active = false;
        this.returnDate = new Date();
        this.status = "Returned";
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", laptopId=" + laptopId +
                ", bookingDate=" + bookingDate +
                ", durationHours=" + durationHours +
                ", totalCharge=" + totalCharge +
                ", status='" + status + '\'' +
                '}';
    }
}