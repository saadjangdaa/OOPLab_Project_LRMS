package com.laptop.rental.repository;

import com.laptop.rental.model.Booking;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepository {
    private static final String FILE_NAME = "bookings.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean save(Booking booking) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            String startDateStr = booking.getStartDate().format(formatter);
            String endDateStr = booking.getEndDate().format(formatter);
            String createdAtStr = booking.getCreatedAt() != null ? booking.getCreatedAt().format(formatter) : "null";
            String cancelledAtStr = booking.getCancelledAt() != null ? booking.getCancelledAt().format(formatter) : "null";
            String returnedAtStr = booking.getReturnedAt() != null ? booking.getReturnedAt().format(formatter) : "null";
            
            writer.printf("BookingID: %s StudentID: %d LaptopID: %d StartDate: %s EndDate: %s Status: %s CreatedAt: %s CancelledAt: %s ReturnedAt: %s%n", 
                    booking.getBookingId(), booking.getStudentId(), booking.getLaptopId(), 
                    startDateStr, endDateStr, booking.getStatus(), createdAtStr, cancelledAtStr, returnedAtStr);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return bookings;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 18) {
                    String bookingId = parts[1];
                    int studentId = Integer.parseInt(parts[3]);
                    int laptopId = Integer.parseInt(parts[5]);
                    LocalDateTime startDate = LocalDateTime.parse(parts[7] + " " + parts[8], formatter);
                    LocalDateTime endDate = LocalDateTime.parse(parts[10] + " " + parts[11], formatter);
                    String status = parts[13];
                    LocalDateTime createdAt = parts[15].equals("null") ? null : LocalDateTime.parse(parts[15] + " " + parts[16], formatter);
                    LocalDateTime cancelledAt = parts[18].equals("null") ? null : LocalDateTime.parse(parts[18] + " " + parts[19], formatter);
                    LocalDateTime returnedAt = parts[20].equals("null") ? null : LocalDateTime.parse(parts[20] + " " + parts[21], formatter);
                    
                    Booking booking = new Booking();
                    booking.setBookingId(bookingId);
                    booking.setStudentId(studentId);
                    booking.setLaptopId(laptopId);
                    booking.setStartDate(startDate);
                    booking.setEndDate(endDate);
                    booking.setStatus(status);
                    booking.setCreatedAt(createdAt);
                    booking.setCancelledAt(cancelledAt);
                    booking.setReturnedAt(returnedAt);
                    
                    bookings.add(booking);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public Optional<Booking> findById(String bookingId) {
        for (Booking booking : findAll()) {
            if (booking.getBookingId().equals(bookingId)) {
                return Optional.of(booking);
            }
        }
        return Optional.empty();
    }

    public List<Booking> findByStudentId(int studentId) {
        List<Booking> studentBookings = new ArrayList<>();
        for (Booking booking : findAll()) {
            if (booking.getStudentId() == studentId) {
                studentBookings.add(booking);
            }
        }
        return studentBookings;
    }

    public List<Booking> findByStatus(String status) {
        List<Booking> statusBookings = new ArrayList<>();
        for (Booking booking : findAll()) {
            if (booking.getStatus().equals(status)) {
                statusBookings.add(booking);
            }
        }
        return statusBookings;
    }

    public boolean update(Booking booking) {
        List<Booking> bookings = findAll();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId().equals(booking.getBookingId())) {
                bookings.set(i, booking);
                return saveAll(bookings);
            }
        }
        return false;
    }

    public boolean delete(String bookingId) {
        List<Booking> bookings = findAll();
        bookings.removeIf(booking -> booking.getBookingId().equals(bookingId));
        return saveAll(bookings);
    }

    private boolean saveAll(List<Booking> bookings) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))) {
            for (Booking booking : bookings) {
                String startDateStr = booking.getStartDate().format(formatter);
                String endDateStr = booking.getEndDate().format(formatter);
                String createdAtStr = booking.getCreatedAt() != null ? booking.getCreatedAt().format(formatter) : "null";
                String cancelledAtStr = booking.getCancelledAt() != null ? booking.getCancelledAt().format(formatter) : "null";
                String returnedAtStr = booking.getReturnedAt() != null ? booking.getReturnedAt().format(formatter) : "null";
                
                writer.printf("BookingID: %s StudentID: %d LaptopID: %d StartDate: %s EndDate: %s Status: %s CreatedAt: %s CancelledAt: %s ReturnedAt: %s%n", 
                        booking.getBookingId(), booking.getStudentId(), booking.getLaptopId(), 
                        startDateStr, endDateStr, booking.getStatus(), createdAtStr, cancelledAtStr, returnedAtStr);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
} 