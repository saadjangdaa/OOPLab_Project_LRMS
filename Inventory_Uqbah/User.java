// User.java

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {

    public static void displayLaptop() {
        File file = new File("Laptop-Data.txt");
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        try {
            Scanner fileScanner = new Scanner(file);
            System.out.println("******Reading File******\n");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            System.out.println("\nFile Read Successfully!\n");
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }

    public static void bookLaptop() {
        displayLaptop();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Laptop ID you want to book: ");
        String bookId = scanner.nextLine();

        File file = new File("Laptop-Data.txt");
        File tempFile = new File("Laptop-Data-temp.txt");
        boolean found = false;

        try {
            Scanner fileScanner = new Scanner(file);
            FileWriter writer = new FileWriter(tempFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                //     if (line.contains("Laptop ID: " + bookId + "\t")) {
                //         found = true;
                //         Admin.updateLaptopStatus(bookId);
                //         continue; // Skip this line (delete)
                //     }
                //     writer.write(line + System.lineSeparator());
                // }
                // fileScanner.close();
                // writer.close();
                String[] parts = line.split("\t");
                String idPart = parts[0].trim();
                String[] id = idPart.split(": ");
                if (id.length > 1 && id[1].equals(bookId)) {
                    found = true;
                    Admin.updateLaptopStatus(bookId);
                }
                writer.write(line + System.lineSeparator());
            }
            fileScanner.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

        if (found) {
            // Replace original file with temp file
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Laptop booked and removed from available list.");
        } else {
            tempFile.delete();
            System.out.println("Laptop ID not found.");
        }
    }
}
