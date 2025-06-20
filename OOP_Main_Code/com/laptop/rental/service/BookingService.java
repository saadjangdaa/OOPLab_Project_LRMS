package com.laptop.rental.service;

import com.laptop.rental.model.Booking;
import com.laptop.rental.model.Student;
import com.laptop.rental.model.Laptop;
import com.laptop.rental.repository.BookingRepository;
import com.laptop.rental.repository.StudentRepository;
import com.laptop.rental.repository.LaptopRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookingService {
    private BookingRepository bookingRepository;
    private StudentRepository studentRepository;
    private LaptopRepository laptopRepository;

    public BookingService() {
        this.bookingRepository = new BookingRepository();
        this.studentRepository = new StudentRepository();
        this.laptopRepository = new LaptopRepository();
    }

    public boolean createBooking(int studentId, int laptopId, int hours) throws IOException {
        Student student = studentRepository.findById(studentId);
        Laptop laptop = laptopRepository.findById(laptopId);
        
        if (student == null) {
            System.out.println("Student not found!");
            return false;
        }
        
        if (laptop == null) {
            System.out.println("Laptop not found!");
            return false;
        }
        
        if (!laptop.isAvailable()) {
            System.out.println("Laptop is not available for booking!");
            return false;
        }
        
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(hours);
        
        Booking booking = new Booking(studentId, laptopId, startTime, endTime, hours);
        bookingRepository.save(booking);
        
        laptop.setAvailable(false);
        laptopRepository.update(laptop);
        
        System.out.println("Booking created successfully!");
        return true;
    }

    public boolean returnLaptop(int bookingId) throws IOException {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            System.out.println("Booking not found!");
            return false;
        }
        
        if (booking.isReturned()) {
            System.out.println("Laptop already returned!");
            return false;
        }
        
        booking.setReturned(true);
        booking.setActualReturnTime(LocalDateTime.now());
        bookingRepository.update(booking);
        
        Laptop laptop = laptopRepository.findById(booking.getLaptopId());
        if (laptop != null) {
            laptop.setAvailable(true);
            laptopRepository.update(laptop);
        }
        
        System.out.println("Laptop returned successfully!");
        return true;
    }

    public boolean cancelBooking(int bookingId) throws IOException {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            System.out.println("Booking not found!");
            return false;
        }
        
        if (booking.isReturned()) {
            System.out.println("Cannot cancel a returned booking!");
            return false;
        }
        
        bookingRepository.delete(bookingId);
        
        Laptop laptop = laptopRepository.findById(booking.getLaptopId());
        if (laptop != null) {
            laptop.setAvailable(true);
            laptopRepository.update(laptop);
        }
        
        System.out.println("Booking cancelled successfully!");
        return true;
    }

    public double calculateRevenue() throws IOException {
        List<Booking> bookings = bookingRepository.findAll();
        double totalRevenue = 0.0;
        
        for (Booking booking : bookings) {
            if (booking.isReturned()) {
                Laptop laptop = laptopRepository.findById(booking.getLaptopId());
                if (laptop != null) {
                    totalRevenue += booking.getHours() * laptop.getHourlyRate();
                }
            }
        }
        
        return totalRevenue;
    }

    public double calculateDebt(int studentId) throws IOException {
        List<Booking> bookings = bookingRepository.findAll();
        double totalDebt = 0.0;
        
        for (Booking booking : bookings) {
            if (booking.getStudentId() == studentId && !booking.isReturned()) {
                Laptop laptop = laptopRepository.findById(booking.getLaptopId());
                if (laptop != null) {
                    totalDebt += booking.getHours() * laptop.getHourlyRate();
                }
            }
        }
        
        return totalDebt;
    }

    public boolean extendBooking(int bookingId, int additionalHours) throws IOException {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            System.out.println("Booking not found!");
            return false;
        }
        
        if (booking.isReturned()) {
            System.out.println("Cannot extend a returned booking!");
            return false;
        }
        
        booking.setEndTime(booking.getEndTime().plusHours(additionalHours));
        booking.setHours(booking.getHours() + additionalHours);
        bookingRepository.update(booking);
        
        System.out.println("Booking extended successfully!");
        return true;
    }

    public List<Booking> getOverdueBookings() throws IOException {
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> overdueBookings = new java.util.ArrayList<>();
        
        for (Booking booking : bookings) {
            if (!booking.isReturned() && LocalDateTime.now().isAfter(booking.getEndTime())) {
                overdueBookings.add(booking);
            }
        }
        
        return overdueBookings;
    }

    public List<Booking> getStudentBookings(int studentId) throws IOException {
        List<Booking> allBookings = bookingRepository.findAll();
        List<Booking> studentBookings = new java.util.ArrayList<>();
        
        for (Booking booking : allBookings) {
            if (booking.getStudentId() == studentId) {
                studentBookings.add(booking);
            }
        }
        
        return studentBookings;
    }

    public List<Booking> getAllBookings() throws IOException {
        return bookingRepository.findAll();
    }
}