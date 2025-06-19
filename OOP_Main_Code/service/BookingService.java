// BookingService.java
package com.laptop.rental.service;

import com.laptop.rental.model.Booking;
import com.laptop.rental.model.Laptop;
import com.laptop.rental.repository.BookingRepository;
import com.laptop.rental.repository.LaptopRepository;
import java.io.IOException;
import java.util.List;

public class BookingService {
    private BookingRepository bookingRepository = new BookingRepository();
    private LaptopRepository laptopRepository = new LaptopRepository();

    public boolean bookLaptop(int studentId, int laptopId, int durationHours) throws IOException {
        Laptop laptop = laptopRepository.findById(laptopId);
        if (laptop == null || !laptop.isAvailable()) {
            return false;
        }

        Booking booking = new Booking(studentId, laptopId, durationHours, laptop.getHourlyRate());
        bookingRepository.save(booking);
        
        laptop.setAvailable(false);
        laptopRepository.update(laptop);
        
        return true;
    }

    public boolean returnLaptop(int studentId, int laptopId) throws IOException {
        boolean success = bookingRepository.markAsReturned(studentId, laptopId);
        if (success) {
            Laptop laptop = laptopRepository.findById(laptopId);
            if (laptop != null) {
                laptop.setAvailable(true);
                laptopRepository.update(laptop);
            }
        }
        return success;
    }

    public List<Booking> getStudentBookings(int studentId) throws IOException {
        return bookingRepository.findByStudentId(studentId);
    }

    public List<Booking> getLaptopBookings(int laptopId) throws IOException {
        return bookingRepository.findByLaptopId(laptopId);
    }

    public List<Booking> getActiveBookings() throws IOException {
        return bookingRepository.findActive();
    }

    public Booking getBookingById(int id) throws IOException {
        return bookingRepository.findById(id);
    }

    public boolean cancelBooking(int bookingId) throws IOException {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null || !booking.isActive()) {
            return false;
        }

        booking.setActive(false);
        booking.setStatus("Cancelled");
        bookingRepository.update(booking);

        // Make laptop available again
        Laptop laptop = laptopRepository.findById(booking.getLaptopId());
        if (laptop != null) {
            laptop.setAvailable(true);
            laptopRepository.update(laptop);
        }

        return true;
    }

    public double calculateTotalRevenue() throws IOException {
        List<Booking> allBookings = bookingRepository.findAll();
        double totalRevenue = 0.0;
        
        for (Booking booking : allBookings) {
            if (!booking.isActive()) { // Only count completed bookings
                totalRevenue += booking.getTotalCharge();
            }
        }
        
        return totalRevenue;
    }

    public double calculateStudentDebt(int studentId) throws IOException {
        List<Booking> studentBookings = bookingRepository.findByStudentId(studentId);
        double totalDebt = 0.0;
        
        for (Booking booking : studentBookings) {
            if (booking.isActive()) { // Only count active bookings
                totalDebt += booking.getTotalCharge();
            }
        }
        
        return totalDebt;
    }

    public boolean extendBooking(int bookingId, int additionalHours) throws IOException {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null || !booking.isActive()) {
            return false;
        }

        booking.setDurationHours(booking.getDurationHours() + additionalHours);
        bookingRepository.update(booking);
        
        return true;
    }

    public List<Booking> getOverdueBookings() throws IOException {
        List<Booking> activeBookings = bookingRepository.findActive();
        List<Booking> overdueBookings = new java.util.ArrayList<>();
        
        java.util.Date now = new java.util.Date();
        long currentTime = now.getTime();
        
        for (Booking booking : activeBookings) {
            long bookingTime = booking.getBookingDate().getTime();
            long durationInMillis = booking.getDurationHours() * 60 * 60 * 1000L; // Convert hours to milliseconds
            
            if (currentTime > bookingTime + durationInMillis) {
                booking.setStatus("Overdue");
                bookingRepository.update(booking);
                overdueBookings.add(booking);
            }
        }
        
        return overdueBookings;
    }
}