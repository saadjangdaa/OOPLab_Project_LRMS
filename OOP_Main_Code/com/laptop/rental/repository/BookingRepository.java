package com.laptop.rental.repository;

import com.laptop.rental.model.Booking;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private static final String FILE_NAME = "bookings.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void save(Booking booking) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            String startTimeStr = booking.getStartTime().format(formatter);
            String endTimeStr = booking.getEndTime().format(formatter);
            String createdAtStr = booking.getCreatedAt() != null ? booking.getCreatedAt().format(formatter) : "null";
            String actualReturnTimeStr = booking.getActualReturnTime() != null ? booking.getActualReturnTime().format(formatter) : "null";
            
            writer.printf("BookingID: %d StudentID: %d LaptopID: %d StartTime: %s EndTime: %s Hours: %d Returned: %s CreatedAt: %s ActualReturnTime: %s%n", 
                    booking.getBookingId(), booking.getStudentId(), booking.getLaptopId(), 
                    startTimeStr, endTimeStr, booking.getHours(), booking.isReturned(), createdAtStr, actualReturnTimeStr);
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
                try {
                    String[] parts = line.split(" ");
                    if (parts.length >= 18) {
                        int bookingId = Integer.parseInt(parts[1]);
                        int studentId = Integer.parseInt(parts[3]);
                        int laptopId = Integer.parseInt(parts[5]);
                        LocalDateTime startTime = LocalDateTime.parse(parts[7] + " " + parts[8], formatter);
                        LocalDateTime endTime = LocalDateTime.parse(parts[10] + " " + parts[11], formatter);
                        int hours = Integer.parseInt(parts[13]);
                        boolean returned = Boolean.parseBoolean(parts[15]);
                        LocalDateTime createdAt = parts[17].equals("null") ? null : LocalDateTime.parse(parts[17] + " " + parts[18], formatter);
                        LocalDateTime actualReturnTime = parts[20].equals("null") ? null : LocalDateTime.parse(parts[20] + " " + parts[21], formatter);
                        
                        Booking booking = new Booking();
                        booking.setBookingId(bookingId);
                        booking.setStudentId(studentId);
                        booking.setLaptopId(laptopId);
                        booking.setStartTime(startTime);
                        booking.setEndTime(endTime);
                        booking.setHours(hours);
                        booking.setReturned(returned);
                        booking.setCreatedAt(createdAt);
                        booking.setActualReturnTime(actualReturnTime);
                        
                        bookings.add(booking);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing booking line: " + line + " - " + e.getMessage());
                }
            }
        }
        return bookings;
    }

    public Booking findById(int bookingId) throws IOException {
        for (Booking booking : findAll()) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    public void update(Booking booking) throws IOException {
        List<Booking> bookings = findAll();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId() == booking.getBookingId()) {
                bookings.set(i, booking);
                break;
            }
        }
        saveAll(bookings);
    }

    public void delete(int bookingId) throws IOException {
        List<Booking> bookings = findAll();
        bookings.removeIf(booking -> booking.getBookingId() == bookingId);
        saveAll(bookings);
    }

    private void saveAll(List<Booking> bookings) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))) {
            for (Booking booking : bookings) {
                String startTimeStr = booking.getStartTime().format(formatter);
                String endTimeStr = booking.getEndTime().format(formatter);
                String createdAtStr = booking.getCreatedAt() != null ? booking.getCreatedAt().format(formatter) : "null";
                String actualReturnTimeStr = booking.getActualReturnTime() != null ? booking.getActualReturnTime().format(formatter) : "null";
                
                writer.printf("BookingID: %d StudentID: %d LaptopID: %d StartTime: %s EndTime: %s Hours: %d Returned: %s CreatedAt: %s ActualReturnTime: %s%n", 
                        booking.getBookingId(), booking.getStudentId(), booking.getLaptopId(), 
                        startTimeStr, endTimeStr, booking.getHours(), booking.isReturned(), createdAtStr, actualReturnTimeStr);
            }
        }
    }
} 