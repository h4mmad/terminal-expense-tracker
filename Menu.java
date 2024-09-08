import java.util.Scanner;

public class Menu {

    public static void displayMainMenu() {
        System.out.println("-----------------------------------\n" +
                "Welcome to Personal Expense Tracker\n" +
                "-----------------------------------\n" +
                "1. Add Expense\n" +
                "2. View Expenses\n" +
                "3. Generate Report\n" +
                "4. Exit\n");        
    }

    public static void displayExpenseTypeMenu() {
        System.out.println("");
        System.out.println("Type of Expense:");
        System.out.println(
                "1. Rent\n" +
                "2. Food\n" +
                "3. Bill\n" +
                "4. Subscription\n" +
                "5. Clothing\n" +
                "6. Other\n"
        );
    }    

    public static int getIntValue(Scanner scanner) {
        while (true) {
            System.out.print("Enter option: ");
            
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    public static double getDoubleValue(Scanner scanner) {
        while (true) {
            System.out.print("Enter amount: ");
            
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
    }
}
