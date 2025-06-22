
import java.util.Scanner;

public class Program {

    public static void keyTracker() {
        Scanner scanner = new Scanner(System.in);
        int key;

        do {
            System.out.println("Here are the Key-Bindings for navigation:-");
            System.out.println("Add Laptop\t[1]");
            System.out.println("Display Laptops\t[2]");
            System.out.println("Book Laptop\t[3]");
            System.out.println("Exit Rental System\t[4]");
            System.out.print("Press any key of your choice: ");
            key = scanner.nextInt();

            switch (key) {
                case 1:
                    Admin.addLaptop();
                    break;
                case 2:
                    User.displayLaptop();
                    break;
                case 3:
                    User.bookLaptop();
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (key != 4);

        scanner.close();
    }

    public static void main(String[] args) {
        Program.keyTracker();
    }
}
