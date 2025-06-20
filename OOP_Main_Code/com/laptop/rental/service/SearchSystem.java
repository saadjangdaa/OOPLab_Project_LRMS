import java.io.*;
import java.util.Scanner;

public class SearchSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        String role = login(username, password);
        if (role == null) {
            System.out.println("Login failed. Exiting program.");
            scanner.close();
            return;
        }
        
        System.out.println("Login successful. Role: " + role);
        
        while (true) {
            System.out.println("\n--- Menu ---");
            if (role.equals("admin")) {
                System.out.println("1. Search student");
                System.out.println("2. Search laptop");
                System.out.println("3. Exit");
            } else if (role.equals("user")) {
                System.out.println("1. Search laptop");
                System.out.println("2. Exit");
            }
            
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (role.equals("admin")) {
                switch (choice) {
                    case 1:
                        searchStudent(scanner);
                        break;
                    case 2:
                        searchLaptop(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } else if (role.equals("user")) {
                switch (choice) {
                    case 1:
                        searchLaptop(scanner);
                        break;
                    case 2:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }
    
    private static String login(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 6) {
                    String fileUsername = parts[1];
                    String filePassword = parts[3];
                    String fileRole = parts[5];
                    
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return fileRole;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read user file.");
        }
        return null;
    }
    
    private static void searchLaptop(Scanner scanner) {
        System.out.print("Enter laptop name or model to search: ");
        String query = scanner.nextLine();
        searchInFile("laptops.txt", query);
    }
    
    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter student ID or Name to search: ");
        String query = scanner.nextLine();
        searchInFile("students.txt", query);
    }
    
    private static void searchInFile(String filename, String searchQuery) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchQuery)) {
                    System.out.println("Found: " + line);
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("No match found in " + filename);
            }
        } catch (IOException e) {
            System.out.println("Cannot read file: " + filename);
        }
    }
}