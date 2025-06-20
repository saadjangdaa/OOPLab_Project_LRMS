// Booking.java
package com.laptop.rental.model;

import java.time.LocalDateTime;

public class Booking {
    private String bookingId;
    private int studentId;
    private int laptopId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime cancelledAt;
    private LocalDateTime returnedAt;

    public Booking() {
    }

    public Booking(String bookingId, int studentId, int laptopId, LocalDateTime startDate, LocalDateTime endDate) {
        this.bookingId = bookingId;
        this.studentId = studentId;
        this.laptopId = laptopId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "ACTIVE";
        this.createdAt = LocalDateTime.now();
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    
    public int getLaptopId() { return laptopId; }
    public void setLaptopId(int laptopId) { this.laptopId = laptopId; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getCancelledAt() { return cancelledAt; }
    public void setCancelledAt(LocalDateTime cancelledAt) { this.cancelledAt = cancelledAt; }

    public LocalDateTime getReturnedAt() { return returnedAt; }
    public void setReturnedAt(LocalDateTime returnedAt) { this.returnedAt = returnedAt; }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", studentId=" + studentId +
                ", laptopId=" + laptopId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}