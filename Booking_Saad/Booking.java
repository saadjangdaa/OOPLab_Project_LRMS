import java.io.*;
import java.util.*;

// Base class for rental items (could be extended for other rentable items)
abstract class RentalItem {
    protected int id;
    protected boolean available;

    public RentalItem(int id) {
        this.id = id;
        this.available = true;
    }

    public abstract String getDetails();
}

// Laptop class inheriting from RentalItem
class Laptop extends RentalItem {
    private String model;
    private String specifications;

    public Laptop(int id, String model, String specs) {
        super(id);
        this.model = model;
        this.specifications = specs;
    }

    @Override
    public String getDetails() {
        return String.format("Laptop ID: %d, Model: %s, Specs: %s", id, model, specifications);
    }
}

// Student class
class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// RentalRecord class to handle booking information
class RentalRecord {
    private Student student;
    private Laptop laptop;
    private Date rentalDate;
    private int durationHours;
    private double chargePerHour;

    public RentalRecord(Student student, Laptop laptop, int durationHours, double chargePerHour) {
        this.student = student;
        this.laptop = laptop;
        this.rentalDate = new Date();
        this.durationHours = durationHours;
        this.chargePerHour = chargePerHour;
    }

    public double calculateTotalCharge() {
        return durationHours * chargePerHour;
    }

    public String getRecordDetails() {
        return String.format("Student ID: %d, Laptop ID: %d, Rental Hours: %d, Total Charge: %.2f",
                student.getId(), laptop.id, durationHours, calculateTotalCharge());
    }
}

// Main system class using composition
class LaptopRentalSystem {
    private List<Laptop> laptops;
    private List<Student> students;
    private List<RentalRecord> rentalRecords;
    private String bookingFile = "BookingData.txt";
    private String rentalDetailsFile = "RentalDetailsData.txt";

    public LaptopRentalSystem() {
        this.laptops = new ArrayList<>();
        this.students = new ArrayList<>();
        this.rentalRecords = new ArrayList<>();
        initializeData();
    }

    private void initializeData() {
        // Initialize some sample data
        students.add(new Student(1, "John Doe"));
        students.add(new Student(2, "Jane Smith"));
        
        laptops.add(new Laptop(12, "Dell XPS", "16GB RAM, 512GB SSD"));
        laptops.add(new Laptop(13, "MacBook Pro", "8GB RAM, 256GB SSD"));
    }

    public void bookLaptop(int studentId, int laptopId, int durationHours) throws IOException {
        Student student = findStudentById(studentId);
        Laptop laptop = findLaptopById(laptopId);

        if (student == null || laptop == null) {
            System.out.println("Invalid student or laptop ID");
            return;
        }

        if (!laptop.available) {
            System.out.println("Laptop ID: " + laptopId + " is already booked.");
            return;
        }

        // Create rental record
        double chargePerHour = 200.0; // Could be configured per laptop
        RentalRecord record = new RentalRecord(student, laptop, durationHours, chargePerHour);
        rentalRecords.add(record);

        // Update laptop status
        laptop.available = false;

        // Save to files
        saveBookingToFile(studentId, laptopId, false);
        saveRentalDetailsToFile(record);

        System.out.println("Booking successful! " + record.getRecordDetails());
    }

    public void returnLaptop(int studentId, int laptopId) throws IOException {
        // Implementation similar to your C code but using objects
        boolean found = false;
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(bookingFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Student ID: " + studentId) && 
                    line.contains("Laptop ID: " + laptopId) && 
                    line.contains("Status: 1")) {
                    line = line.replace("Status: 1", "Status: 0");
                    found = true;
                }
                lines.add(line);
            }
        }

        if (found) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(bookingFile))) {
                for (String line : lines) {
                    writer.println(line);
                }
            }
            System.out.println("Laptop returned successfully.");
            
            // Update laptop status in memory
            Laptop laptop = findLaptopById(laptopId);
            if (laptop != null) {
                laptop.available = true;
            }
        } else {
            System.out.println("Booking not found or already returned.");
        }
    }

    private Student findStudentById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private Laptop findLaptopById(int id) {
        return laptops.stream()
                .filter(l -> l.id == id)
                .findFirst()
                .orElse(null);
    }

    private void saveBookingToFile(int studentId, int laptopId, boolean available) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(bookingFile, true))) {
            writer.printf("Student ID: %d, Laptop ID: %d, Status: %d%n", 
                    studentId, laptopId, available ? 1 : 0);
        }
    }

    private void saveRentalDetailsToFile(RentalRecord record) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rentalDetailsFile, true))) {
            writer.println(record.getRecordDetails());
        }
    }
}

// Main class to run the system
public class Main {
    public static void main(String[] args) {
        LaptopRentalSystem system = new LaptopRentalSystem();
        
        try {
            system.bookLaptop(1, 12, 48);
            // system.returnLaptop(1, 12);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}