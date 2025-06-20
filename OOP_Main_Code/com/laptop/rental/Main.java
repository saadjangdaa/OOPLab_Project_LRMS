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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            scanner.nextLine();

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
            authService.signup(username, password, 1);
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
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

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
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

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
            scanner.nextLine();

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
            scanner.nextLine();

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
            scanner.nextLine();
            
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
            scanner.nextLine();
            
            System.out.print("Enter brand: ");
            String brand = scanner.nextLine();
            
            System.out.print("Enter model: ");
            String model = scanner.nextLine();
            
            System.out.print("Enter specifications: ");
            String specs = scanner.nextLine();
            
            System.out.print("Enter hourly rate: ");
            double rate = scanner.nextDouble();
            scanner.nextLine();
            
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
            scanner.nextLine();
            
            System.out.print("Enter laptop ID to book: ");
            int laptopId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Enter number of hours to book: ");
            int hours = scanner.nextInt();
            scanner.nextLine();

            if (bookingService.createBooking(studentId, laptopId, hours)) {
                System.out.println("Laptop booked successfully!");
            } else {
                System.out.println("Failed to book laptop. Laptop might not be available or you already have an active booking.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnLaptop() {
        try {
            System.out.print("Enter booking ID to return: ");
            int bookingId = scanner.nextInt();
            scanner.nextLine();
            
            if (bookingService.returnLaptop(bookingId)) {
                System.out.println("Laptop returned successfully!");
            } else {
                System.out.println("Failed to return laptop. Booking might not exist or is not active.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewMyBookings() {
        try {
            System.out.print("Enter your student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            
            List<Booking> bookings = bookingService.getStudentBookings(studentId);
            if (bookings.isEmpty()) {
                System.out.println("No bookings found for this student.");
                return;
            }
            
            System.out.println("\n=== MY BOOKINGS ===");
            System.out.printf("%-15s %-15s %-20s %-20s %-10s%n", 
                "BookingID", "LaptopID", "StartTime", "EndTime", "Returned");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Booking booking : bookings) {
                System.out.printf("%-15d %-15d %-20s %-20s %-10s%n", 
                    booking.getBookingId(), booking.getLaptopId(), 
                    booking.getStartTime().toString().substring(0, 16), 
                    booking.getEndTime().toString().substring(0, 16), 
                    booking.isReturned() ? "Yes" : "No");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllBookings() {
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            if (bookings.isEmpty()) {
                System.out.println("No bookings found.");
                return;
            }
            
            System.out.println("\n=== ALL BOOKINGS ===");
            System.out.printf("%-15s %-15s %-15s %-20s %-20s %-10s%n", 
                "BookingID", "StudentID", "LaptopID", "StartTime", "EndTime", "Returned");
            System.out.println("--------------------------------------------------------------------------------");
            
            for (Booking booking : bookings) {
                System.out.printf("%-15d %-15d %-15d %-20s %-20s %-10s%n", 
                    booking.getBookingId(), booking.getStudentId(), booking.getLaptopId(), 
                    booking.getStartTime().toString().substring(0, 16), 
                    booking.getEndTime().toString().substring(0, 16), 
                    booking.isReturned() ? "Yes" : "No");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewSystemStatistics() {
        try {
            int totalLaptops = laptopService.getTotalLaptopCount();
            int availableLaptops = laptopService.getAvailableLaptopCount();
            
            System.out.println("\n=== SYSTEM STATISTICS ===");
            System.out.println("Total Laptops: " + totalLaptops);
            System.out.println("Available Laptops: " + availableLaptops);
            System.out.println("Rented Laptops: " + (totalLaptops - availableLaptops));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
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
            scanner.nextLine();
            
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

    private static void updateStudent() {
        try {
            System.out.print("Enter student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Student student = studentService.getStudentById(id);
            if (student == null) {
                System.out.println("Student not found with ID: " + id);
                return;
            }
            
            System.out.println("Current student information:");
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone: " + student.getPhoneNumber());
            System.out.println("Department: " + student.getDepartment());
            System.out.println();
            
            System.out.print("Enter new name (or press Enter to keep current): ");
            String name = scanner.nextLine();
            if (name.isEmpty()) name = student.getName();
            
            System.out.print("Enter new email (or press Enter to keep current): ");
            String email = scanner.nextLine();
            if (email.isEmpty()) email = student.getEmail();
            
            System.out.print("Enter new phone number (or press Enter to keep current): ");
            String phone = scanner.nextLine();
            if (phone.isEmpty()) phone = student.getPhoneNumber();
            
            System.out.print("Enter new department (or press Enter to keep current): ");
            String department = scanner.nextLine();
            if (department.isEmpty()) department = student.getDepartment();

            if (studentService.updateStudent(id, name, email, phone, department)) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Failed to update student.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        try {
            System.out.print("Enter student ID to delete: ");
            int id = scanner.nextInt();
            
            Student student = studentService.getStudentById(id);
            if (student == null) {
                System.out.println("Student not found with ID: " + id);
                return;
            }
            
            System.out.println("Student to delete:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Department: " + student.getDepartment());
            
            System.out.print("Are you sure you want to delete this student? (y/n): ");
            String confirm = scanner.next();
            
            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                if (studentService.deleteStudent(id)) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Failed to delete student.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateLaptop() {
        try {
            System.out.print("Enter laptop ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Laptop laptop = laptopService.getLaptopById(id);
            if (laptop == null) {
                System.out.println("Laptop not found with ID: " + id);
                return;
            }
            
            System.out.println("Current laptop information:");
            System.out.println("Brand: " + laptop.getBrand());
            System.out.println("Model: " + laptop.getModel());
            System.out.println("Specifications: " + laptop.getSpecifications());
            System.out.println("Hourly Rate: " + laptop.getHourlyRate());
            System.out.println("Condition: " + laptop.getCondition());
            System.out.println("Available: " + laptop.isAvailable());
            System.out.println();
            
            System.out.print("Enter new brand (or press Enter to keep current): ");
            String brand = scanner.nextLine();
            if (brand.isEmpty()) brand = laptop.getBrand();
            
            System.out.print("Enter new model (or press Enter to keep current): ");
            String model = scanner.nextLine();
            if (model.isEmpty()) model = laptop.getModel();
            
            System.out.print("Enter new specifications (or press Enter to keep current): ");
            String specs = scanner.nextLine();
            if (specs.isEmpty()) specs = laptop.getSpecifications();
            
            System.out.print("Enter new hourly rate (or press Enter to keep current): ");
            String rateStr = scanner.nextLine();
            double rate = laptop.getHourlyRate();
            if (!rateStr.isEmpty()) {
                try {
                    rate = Double.parseDouble(rateStr);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rate format. Keeping current rate.");
                }
            }
            
            System.out.print("Enter new condition (New/Good/Fair/Poor) (or press Enter to keep current): ");
            String condition = scanner.nextLine();
            if (condition.isEmpty()) condition = laptop.getCondition();

            if (laptopService.updateLaptop(id, brand, model, specs, rate, condition)) {
                System.out.println("Laptop updated successfully!");
            } else {
                System.out.println("Failed to update laptop.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteLaptop() {
        try {
            System.out.print("Enter laptop ID to delete: ");
            int id = scanner.nextInt();
            
            Laptop laptop = laptopService.getLaptopById(id);
            if (laptop == null) {
                System.out.println("Laptop not found with ID: " + id);
                return;
            }
            
            System.out.println("Laptop to delete:");
            System.out.println("ID: " + laptop.getId());
            System.out.println("Brand: " + laptop.getBrand());
            System.out.println("Model: " + laptop.getModel());
            System.out.println("Specifications: " + laptop.getSpecifications());
            System.out.println("Available: " + laptop.isAvailable());
            
            System.out.print("Are you sure you want to delete this laptop? (y/n): ");
            String confirm = scanner.next();
            
            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                if (laptopService.deleteLaptop(id)) {
                    System.out.println("Laptop deleted successfully!");
                } else {
                    System.out.println("Failed to delete laptop.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void setLaptopAvailability() {
        try {
            System.out.print("Enter laptop ID: ");
            int id = scanner.nextInt();
            
            Laptop laptop = laptopService.getLaptopById(id);
            if (laptop == null) {
                System.out.println("Laptop not found with ID: " + id);
                return;
            }
            
            System.out.println("Current laptop information:");
            System.out.println("ID: " + laptop.getId());
            System.out.println("Brand: " + laptop.getBrand());
            System.out.println("Model: " + laptop.getModel());
            System.out.println("Currently Available: " + laptop.isAvailable());
            System.out.println();
            
            System.out.print("Set availability (1 for Available, 0 for Not Available): ");
            int availabilityChoice = scanner.nextInt();
            boolean available = (availabilityChoice == 1);
            
            if (laptopService.setLaptopAvailability(id, available)) {
                System.out.println("Laptop availability updated successfully!");
                System.out.println("Laptop is now " + (available ? "Available" : "Not Available"));
            } else {
                System.out.println("Failed to update laptop availability.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}