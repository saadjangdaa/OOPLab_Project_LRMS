package com.laptop.rental.repository;

import com.laptop.rental.model.Booking;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingRepository {
    private static final String FILE_NAME = "bookings.txt";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void save(Booking booking) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            String bookingDateStr = dateFormat.format(booking.getBookingDate());
            String returnDateStr = booking.getReturnDate() != null ? dateFormat.format(booking.getReturnDate()) : "null";
            
            writer.printf("ID: %d StudentID: %d LaptopID: %d BookingDate: %s ReturnDate: %s Duration: %d Rate: %.2f Active: %s Status: %s%n", 
                    booking.getId(), booking.getStudentId(), booking.getLaptopId(), 
                    bookingDateStr, returnDateStr, booking.getDurationHours(), 
                    booking.getChargePerHour(), booking.isActive(), booking.getStatus());
        }
    }

    public List<Booking> findAll() throws IOException {
        List<Booking> bookings = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return bookings;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 20) {
                    int id = Integer.parseInt(parts[1]);
                    int studentId = Integer.parseInt(parts[3]);
                    int laptopId = Integer.parseInt(parts[5]);
                    Date bookingDate = dateFormat.parse(parts[7] + " " + parts[8]);
                    Date returnDate = parts[10].equals("null") ? null : dateFormat.parse(parts[10] + " " + parts[11]);
                    int duration = Integer.parseInt(parts[13]);
                    double rate = Double.parseDouble(parts[15]);
                    boolean active = Boolean.parseBoolean(parts[17]);
                    String status = parts[19];
                    
                    Booking booking = new Booking(id, studentId, laptopId, bookingDate, returnDate, duration, rate, active, status);
                    bookings.add(booking);
                }
            }
        } catch (Exception e) {
            throw new IOException("Error parsing booking data: " + e.getMessage());
        }
        return bookings;
    }

    public Booking findById(int id) throws IOException {
        for (Booking booking : findAll()) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }

    public List<Booking> findByStudentId(int studentId) throws IOException {
        List<Booking> studentBookings = new ArrayList<>();
        for (Booking booking : findAll()) {
            if (booking.getStudentId() == studentId) {
                studentBookings.add(booking);
            }
        }
        return studentBookings;
    }

    public List<Booking> findByLaptopId(int laptopId) throws IOException {
        List<Booking> laptopBookings = new ArrayList<>();
        for (Booking booking : findAll()) {
            if (booking.getLaptopId() == laptopId) {
                laptopBookings.add(booking);
            }
        }
        return laptopBookings;
    }

    public List<Booking> findActive() throws IOException {
        List<Booking> activeBookings = new ArrayList<>();
        for (Booking booking : findAll()) {
            if (booking.isActive()) {
                activeBookings.add(booking);
            }
        }
        return activeBookings;
    }

    public boolean markAsReturned(int studentId, int laptopId) throws IOException {
        List<Booking> bookings = findAll();
        for (Booking booking : bookings) {
            if (booking.getStudentId() == studentId && booking.getLaptopId() == laptopId && booking.isActive()) {
                booking.markAsReturned();
                saveAll(bookings);
                return true;
            }
        }
        return false;
    }

    public void update(Booking booking) throws IOException {
        List<Booking> bookings = findAll();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getId() == booking.getId()) {
                bookings.set(i, booking);
                break;
            }
        }
        saveAll(bookings);
    }

    public void delete(int id) throws IOException {
        List<Booking> bookings = findAll();
        bookings.removeIf(booking -> booking.getId() == id);
        saveAll(bookings);
    }

    private void saveAll(List<Booking> bookings) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))) {
            for (Booking booking : bookings) {
                String bookingDateStr = dateFormat.format(booking.getBookingDate());
                String returnDateStr = booking.getReturnDate() != null ? dateFormat.format(booking.getReturnDate()) : "null";
                
                writer.printf("ID: %d StudentID: %d LaptopID: %d BookingDate: %s ReturnDate: %s Duration: %d Rate: %.2f Active: %s Status: %s%n", 
                        booking.getId(), booking.getStudentId(), booking.getLaptopId(), 
                        bookingDateStr, returnDateStr, booking.getDurationHours(), 
                        booking.getChargePerHour(), booking.isActive(), booking.getStatus());
            }
        }
    }
} 