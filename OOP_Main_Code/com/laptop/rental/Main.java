package com.laptop.rental;

import com.laptop.rental.model.User;
import com.laptop.rental.model.Student;
import com.laptop.rental.model.Laptop;
import com.laptop.rental.model.Booking;
import com.laptop.rental.service.AuthService;
import com.laptop.rental.service.BookingService;
import com.laptop.rental.service.LaptopService;
import com.laptop.rental.service.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static StudentService studentService = new StudentService();
    private static LaptopService laptopService = new LaptopService();
    private static BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        initializeSystem();
        
        while (true) {
            System.out.println("\n=== Welcome to the Laptop Rental Management System ===");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeSystem() {
        try {
            // Create default admin account if it doesn't exist
            authService.signup("admin", "admin123", 0);
            System.out.println("System initialized successfully!");
        } catch (IOException e) {
            System.out.println("Error initializing system: " + e.getMessage());
        }
    }

    private static void login() {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = authService.login(username, password);
            if (user != null) {
                System.out.println("Login successful! Welcome, " + username + ".");
                if (user.isAdmin()) {
                    adminMenu();
                } else {
                    studentMenu();
                }
            } else {
                System.out.println("Login failed. Invalid username or password.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void signup() {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            authService.signup(username, password, 1); // Default role is student
            System.out.println("Signup successful! You can now login.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Laptops");
            System.out.println("3. View All Bookings");
            System.out.println("4. View System Statistics");
            System.out.println("5. View Overdue Bookings");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageLaptops();
                    break;
                case 3:
                    viewAllBookings();
                    break;
                case 4:
                    viewSystemStatistics();
                    break;
                case 5:
                    viewOverdueBookings();
                    break;
                case 6:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void studentMenu() {
        while (true) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. View Available Laptops");
            System.out.println("2. Search Laptops");
            System.out.println("3. Book a Laptop");
            System.out.println("4. Return a Laptop");
            System.out.println("5. View My Bookings");
            System.out.println("6. Extend Booking");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableLaptops();
                    break;
                case 2:
                    searchLaptops();
                    break;
                case 3:
                    bookLaptop();
                    break;
                case 4:
                    returnLaptop();
                    break;
                case 5:
                    viewMyBookings();
                    break;
                case 6:
                    extendBooking();
                    break;
                case 7:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageStudents() {
        while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT ===");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageLaptops() {
        while (true) {
            System.out.println("\n=== LAPTOP MANAGEMENT ===");
            System.out.println("1. Add New Laptop");
            System.out.println("2. View All Laptops");
            System.out.println("3. Update Laptop");
            System.out.println("4. Delete Laptop");
            System.out.println("5. Set Laptop Availability");
            System.out.println("6. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewLaptop();
                    break;
                case 2:
                    viewAllLaptops();
                    break;
                case 3:
                    updateLaptop();
                    break;
                case 4:
                    deleteLaptop();
                    break;
                case 5:
                    setLaptopAvailability();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewStudent() {
        try {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter phone number: ");
            String phone = scanner.nextLine();
            
            System.out.print("Enter department: ");
            String department = scanner.nextLine();

            if (studentService.registerStudent(id, name, password, email, phone, department)) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Failed to add student. Student ID might already exist.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }
            
            System.out.println("\n=== ALL STUDENTS ===");
            System.out.printf("%-5s %-20s %-25s %-15s %-15s%n", "ID", "Name", "Email", "Phone", "Department");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Student student : students) {
                System.out.printf("%-5d %-20s %-25s %-15s %-15s%n", 
                    student.getId(), student.getName(), student.getEmail(), 
                    student.getPhoneNumber(), student.getDepartment());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addNewLaptop() {
        try {
            System.out.print("Enter laptop ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter brand: ");
            String brand = scanner.nextLine();
            
            System.out.print("Enter model: ");
            String model = scanner.nextLine();
            
            System.out.print("Enter specifications: ");
            String specs = scanner.nextLine();
            
            System.out.print("Enter hourly rate: ");
            double rate = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter condition (New/Good/Fair/Poor): ");
            String condition = scanner.nextLine();

            if (laptopService.addLaptop(id, brand, model, specs, rate, condition)) {
                System.out.println("Laptop added successfully!");
            } else {
                System.out.println("Failed to add laptop. Laptop ID might already exist.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllLaptops() {
        try {
            List<Laptop> laptops = laptopService.getAllLaptops();
            if (laptops.isEmpty()) {
                System.out.println("No laptops found.");
                return;
            }
            
            System.out.println("\n=== ALL LAPTOPS ===");
            System.out.printf("%-5s %-15s %-15s %-20s %-10s %-10s %-10s%n", 
                "ID", "Brand", "Model", "Specifications", "Available", "Rate", "Condition");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Laptop laptop : laptops) {
                System.out.printf("%-5d %-15s %-15s %-20s %-10s %-10.2f %-10s%n", 
                    laptop.getId(), laptop.getBrand(), laptop.getModel(), 
                    laptop.getSpecifications(), laptop.isAvailable(), 
                    laptop.getHourlyRate(), laptop.getCondition());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAvailableLaptops() {
        try {
            List<Laptop> laptops = laptopService.getAvailableLaptops();
            if (laptops.isEmpty()) {
                System.out.println("No available laptops found.");
                return;
            }
            
            System.out.println("\n=== AVAILABLE LAPTOPS ===");
            System.out.printf("%-5s %-15s %-15s %-20s %-10s %-10s%n", 
                "ID", "Brand", "Model", "Specifications", "Rate", "Condition");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Laptop laptop : laptops) {
                System.out.printf("%-5d %-15s %-15s %-20s %-10.2f %-10s%n", 
                    laptop.getId(), laptop.getBrand(), laptop.getModel(), 
                    laptop.getSpecifications(), laptop.getHourlyRate(), laptop.getCondition());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void bookLaptop() {
        try {
            System.out.print("Enter your student ID: ");
            int studentId = scanner.nextInt();
            
            System.out.print("Enter laptop ID to book: ");
            int laptopId = scanner.nextInt();
            
            System.out.print("Enter duration (hours): ");
            int duration = scanner.nextInt();

            if (bookingService.bookLaptop(studentId, laptopId, duration)) {
                System.out.println("Laptop booked successfully!");
            } else {
                System.out.println("Failed to book laptop. Laptop might not be available.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnLaptop() {
        try {
            System.out.print("Enter your student ID: ");
            int studentId = scanner.nextInt();
            
            System.out.print("Enter laptop ID to return: ");
            int laptopId = scanner.nextInt();

            if (bookingService.returnLaptop(studentId, laptopId)) {
                System.out.println("Laptop returned successfully!");
            } else {
                System.out.println("Failed to return laptop. Booking might not exist.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewMyBookings() {
        try {
            System.out.print("Enter your student ID: ");
            int studentId = scanner.nextInt();
            
            List<Booking> bookings = bookingService.getStudentBookings(studentId);
            if (bookings.isEmpty()) {
                System.out.println("No bookings found for this student.");
                return;
            }
            
            System.out.println("\n=== MY BOOKINGS ===");
            System.out.printf("%-5s %-10s %-15s %-20s %-10s %-10s %-10s%n", 
                "ID", "LaptopID", "BookingDate", "Duration", "Rate", "Total", "Status");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Booking booking : bookings) {
                System.out.printf("%-5d %-10d %-15s %-20d %-10.2f %-10.2f %-10s%n", 
                    booking.getId(), booking.getLaptopId(), 
                    booking.getBookingDate().toString().substring(0, 16), 
                    booking.getDurationHours(), booking.getChargePerHour(), 
                    booking.getTotalCharge(), booking.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllBookings() {
        try {
            List<Booking> bookings = bookingService.getActiveBookings();
            if (bookings.isEmpty()) {
                System.out.println("No active bookings found.");
                return;
            }
            
            System.out.println("\n=== ALL ACTIVE BOOKINGS ===");
            System.out.printf("%-5s %-10s %-10s %-15s %-10s %-10s %-10s%n", 
                "ID", "StudentID", "LaptopID", "BookingDate", "Duration", "Total", "Status");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Booking booking : bookings) {
                System.out.printf("%-5d %-10d %-10d %-15s %-10d %-10.2f %-10s%n", 
                    booking.getId(), booking.getStudentId(), booking.getLaptopId(), 
                    booking.getBookingDate().toString().substring(0, 16), 
                    booking.getDurationHours(), booking.getTotalCharge(), booking.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewSystemStatistics() {
        try {
            int totalLaptops = laptopService.getTotalLaptopCount();
            int availableLaptops = laptopService.getAvailableLaptopCount();
            double totalRevenue = bookingService.calculateTotalRevenue();
            
            System.out.println("\n=== SYSTEM STATISTICS ===");
            System.out.println("Total Laptops: " + totalLaptops);
            System.out.println("Available Laptops: " + availableLaptops);
            System.out.println("Rented Laptops: " + (totalLaptops - availableLaptops));
            System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewOverdueBookings() {
        try {
            List<Booking> overdueBookings = bookingService.getOverdueBookings();
            if (overdueBookings.isEmpty()) {
                System.out.println("No overdue bookings found.");
                return;
            }
            
            System.out.println("\n=== OVERDUE BOOKINGS ===");
            System.out.printf("%-5s %-10s %-10s %-15s %-10s %-10s%n", 
                "ID", "StudentID", "LaptopID", "BookingDate", "Duration", "Total");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Booking booking : overdueBookings) {
                System.out.printf("%-5d %-10d %-10d %-15s %-10d %-10.2f%n", 
                    booking.getId(), booking.getStudentId(), booking.getLaptopId(), 
                    booking.getBookingDate().toString().substring(0, 16), 
                    booking.getDurationHours(), booking.getTotalCharge());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void extendBooking() {
        try {
            System.out.print("Enter booking ID: ");
            int bookingId = scanner.nextInt();
            
            System.out.print("Enter additional hours: ");
            int additionalHours = scanner.nextInt();

            if (bookingService.extendBooking(bookingId, additionalHours)) {
                System.out.println("Booking extended successfully!");
            } else {
                System.out.println("Failed to extend booking. Booking might not exist or be inactive.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Placeholder methods for admin functions
    private static void updateStudent() {
        System.out.println("Update student functionality - to be implemented");
    }

    private static void deleteStudent() {
        System.out.println("Delete student functionality - to be implemented");
    }

    private static void updateLaptop() {
        System.out.println("Update laptop functionality - to be implemented");
    }

    private static void deleteLaptop() {
        System.out.println("Delete laptop functionality - to be implemented");
    }

    private static void setLaptopAvailability() {
        System.out.println("Set laptop availability functionality - to be implemented");
    }

    private static void searchLaptops() {
        try {
            System.out.println("\n=== SEARCH LAPTOPS ===");
            System.out.println("1. Search by Brand");
            System.out.println("2. Search by Model");
            System.out.println("3. Search by Specifications");
            System.out.println("4. General Search (All Fields)");
            System.out.println("5. Search Available Laptops Only");
            System.out.println("6. Back to Student Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            String searchQuery = "";
            switch (choice) {
                case 1:
                    System.out.print("Enter brand name to search: ");
                    searchQuery = scanner.nextLine();
                    searchLaptopsByCriteria("brand", searchQuery, false);
                    break;
                case 2:
                    System.out.print("Enter model name to search: ");
                    searchQuery = scanner.nextLine();
                    searchLaptopsByCriteria("model", searchQuery, false);
                    break;
                case 3:
                    System.out.print("Enter specifications to search: ");
                    searchQuery = scanner.nextLine();
                    searchLaptopsByCriteria("specifications", searchQuery, false);
                    break;
                case 4:
                    System.out.print("Enter search term (searches all fields): ");
                    searchQuery = scanner.nextLine();
                    searchLaptopsByCriteria("general", searchQuery, false);
                    break;
                case 5:
                    System.out.print("Enter search term for available laptops: ");
                    searchQuery = scanner.nextLine();
                    searchLaptopsByCriteria("general", searchQuery, true);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error during search: " + e.getMessage());
        }
    }
    
    private static void searchLaptopsByCriteria(String criteria, String searchQuery, boolean availableOnly) {
        try {
            List<Laptop> allLaptops = laptopService.getAllLaptops();
            List<Laptop> searchResults = new ArrayList<>();
            
            for (Laptop laptop : allLaptops) {
                boolean matches = false;
                switch (criteria.toLowerCase()) {
                    case "brand":
                        matches = laptop.getBrand().toLowerCase().contains(searchQuery.toLowerCase());
                        break;
                    case "model":
                        matches = laptop.getModel().toLowerCase().contains(searchQuery.toLowerCase());
                        break;
                    case "specifications":
                        matches = laptop.getSpecifications().toLowerCase().contains(searchQuery.toLowerCase());
                        break;
                    case "general":
                        // Search across all fields
                        matches = laptop.getBrand().toLowerCase().contains(searchQuery.toLowerCase()) ||
                                 laptop.getModel().toLowerCase().contains(searchQuery.toLowerCase()) ||
                                 laptop.getSpecifications().toLowerCase().contains(searchQuery.toLowerCase());
                        break;
                }
                
                if (matches && (!availableOnly || laptop.isAvailable())) {
                    searchResults.add(laptop);
                }
            }
            
            if (searchResults.isEmpty()) {
                if (availableOnly) {
                    System.out.println("No available laptops found matching your search criteria.");
                } else {
                    System.out.println("No laptops found matching your search criteria.");
                }
                return;
            }
            
            String title = availableOnly ? "=== SEARCH RESULTS (Available Laptops Only) ===" : "=== SEARCH RESULTS ===";
            System.out.println("\n" + title);
            System.out.printf("%-5s %-15s %-15s %-20s %-10s %-10s %-10s%n", 
                "ID", "Brand", "Model", "Specifications", "Available", "Rate", "Condition");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Laptop laptop : searchResults) {
                System.out.printf("%-5d %-15s %-15s %-20s %-10s %-10.2f %-10s%n", 
                    laptop.getId(), laptop.getBrand(), laptop.getModel(), 
                    laptop.getSpecifications(), laptop.isAvailable(), 
                    laptop.getHourlyRate(), laptop.getCondition());
            }
            
            String resultMessage = availableOnly ? 
                "Found " + searchResults.size() + " available laptop(s) matching your search." :
                "Found " + searchResults.size() + " laptop(s) matching your search.";
            System.out.println("\n" + resultMessage);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}