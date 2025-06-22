import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin {
    
    public static void addLaptop() {
        Scanner scanner = new Scanner(System.in);
        try {
            FileWriter writer = new FileWriter("Laptop-Data.txt", true);
            System.out.print("Enter laptop ID: ");
            String laptopId = scanner.nextLine();
            writer.write("Laptop ID: " + laptopId + "\t");

            System.out.print("Enter Laptop Brand: ");
            String laptopBrand = scanner.nextLine();
            writer.write("Brand: " + laptopBrand + "\t");

            System.out.print("Enter laptop Model: ");
            String laptopModel = scanner.nextLine();
            writer.write("Model: " + laptopModel + "\t");

            System.out.print("Enter laptop Processor: ");
            String laptopProcessor = scanner.nextLine();
            writer.write("Processor: " + laptopProcessor + "\t");

            System.out.print("Enter laptop Specifications: ");
            String laptopSpecs = scanner.nextLine();
            writer.write("Specs: " + laptopSpecs + "\n");

            writer.close();
            System.out.println("Laptop added successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the laptop.");
            e.printStackTrace();
        }
    }

    public static void updateLaptopStatus(String id) {
        File file = new File("Laptop-Data.txt");
        File tempFile = new File("Laptop-Data-temp.txt");
        boolean found = false;

        try {
            Scanner fileScanner = new Scanner(file);
            FileWriter writer = new FileWriter(tempFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains("Laptop ID: " + id + "\t")) {
                    found = true;
                    // Skip this line (delete)
                    continue;
                }
                writer.write(line + System.lineSeparator());
            }
            fileScanner.close();
            writer.close();

            if (found) {
                if (file.delete()) {
                    tempFile.renameTo(file);
                    System.out.println("Laptop with ID " + id + " has been booked and removed from the list.");
                } else {
                    System.out.println("Error updating file.");
                }
            } else {
                tempFile.delete();
                System.out.println("Laptop ID not found.");
            }
        } catch (IOException e) {
            System.out.println("Error processing file.");
        }

        // Call keyTracker to return to menu
        Program.keyTracker();
    }
}