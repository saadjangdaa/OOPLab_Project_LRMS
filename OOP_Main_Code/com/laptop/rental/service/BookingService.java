package com.laptop.rental.service;

import com.laptop.rental.model.Booking;
import com.laptop.rental.model.Student;
import com.laptop.rental.model.Laptop;
import com.laptop.rental.repository.BookingRepository;
import com.laptop.rental.repository.StudentRepository;
import com.laptop.rental.repository.LaptopRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BookingService {
    private BookingRepository bookingRepository;
    private StudentRepository studentRepository;
    private LaptopRepository laptopRepository;

    public BookingService() {
        this.bookingRepository = new BookingRepository();
        this.studentRepository = new StudentRepository();
        this.laptopRepository = new LaptopRepository();
    }

    public boolean createBooking(int studentId, int laptopId, LocalDateTime startDate, LocalDateTime endDate) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Laptop> laptopOpt = laptopRepository.findById(laptopId);

        if (!studentOpt.isPresent() || !laptopOpt.isPresent()) {
            return false;
        }

        Student student = studentOpt.get();
        Laptop laptop = laptopOpt.get();

        if (!laptop.isAvailable()) {
            return false;
        }

        List<Booking> existingBookings = bookingRepository.findByStudentId(studentId);
        for (Booking booking : existingBookings) {
            if (booking.getStatus().equals("ACTIVE")) {
                return false;
            }
        }

        Booking booking = new Booking();
        booking.setBookingId(generateBookingId());
        booking.setStudentId(studentId);
        booking.setLaptopId(laptopId);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setStatus("ACTIVE");
        booking.setCreatedAt(LocalDateTime.now());

        boolean bookingSaved = bookingRepository.save(booking);
        
        if (bookingSaved) {
        laptop.setAvailable(false);
        laptopRepository.update(laptop);
        }
        
        return bookingSaved;
    }

    public boolean cancelBooking(String bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (!bookingOpt.isPresent()) {
            return false;
        }

        Booking booking = bookingOpt.get();
        if (!booking.getStatus().equals("ACTIVE")) {
            return false;
        }

        booking.setStatus("CANCELLED");
        booking.setCancelledAt(LocalDateTime.now());

        boolean bookingUpdated = bookingRepository.update(booking);
        
        if (bookingUpdated) {
            Optional<Laptop> laptopOpt = laptopRepository.findById(booking.getLaptopId());
            if (laptopOpt.isPresent()) {
                Laptop laptop = laptopOpt.get();
                laptop.setAvailable(true);
                laptopRepository.update(laptop);
            }
        }

        return bookingUpdated;
    }

    public boolean returnLaptop(String bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (!bookingOpt.isPresent()) {
            return false;
        }

        Booking booking = bookingOpt.get();
        if (!booking.getStatus().equals("ACTIVE")) {
            return false;
        }

        booking.setStatus("RETURNED");
        booking.setReturnedAt(LocalDateTime.now());

        boolean bookingUpdated = bookingRepository.update(booking);

        if (bookingUpdated) {
            Optional<Laptop> laptopOpt = laptopRepository.findById(booking.getLaptopId());
            if (laptopOpt.isPresent()) {
                Laptop laptop = laptopOpt.get();
            laptop.setAvailable(true);
            laptopRepository.update(laptop);
            }
        }

        return bookingUpdated;
    }

    public List<Booking> getBookingsByStudent(int studentId) {
        return bookingRepository.findByStudentId(studentId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public List<Booking> getActiveBookings() {
        return bookingRepository.findByStatus("ACTIVE");
    }

    private String generateBookingId() {
        return "BK" + System.currentTimeMillis();
    }
}