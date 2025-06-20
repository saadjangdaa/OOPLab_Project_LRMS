// Booking.java
package com.laptop.rental.model;

import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private int studentId;
    private int laptopId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int hours;
    private boolean returned;
    private LocalDateTime actualReturnTime;
    private LocalDateTime createdAt;

    public Booking() {
        this.createdAt = LocalDateTime.now();
    }

    public Booking(int studentId, int laptopId, LocalDateTime startTime, LocalDateTime endTime, int hours) {
        this.bookingId = generateBookingId();
        this.studentId = studentId;
        this.laptopId = laptopId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hours = hours;
        this.returned = false;
        this.createdAt = LocalDateTime.now();
    }

    private int generateBookingId() {
        return (int) (System.currentTimeMillis() % 1000000);
    }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    
    public int getLaptopId() { return laptopId; }
    public void setLaptopId(int laptopId) { this.laptopId = laptopId; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }
    
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
    
    public LocalDateTime getActualReturnTime() { return actualReturnTime; }
    public void setActualReturnTime(LocalDateTime actualReturnTime) { this.actualReturnTime = actualReturnTime; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", studentId=" + studentId +
                ", laptopId=" + laptopId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", hours=" + hours +
                ", returned=" + returned +
                '}';
    }
}