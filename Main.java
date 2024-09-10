import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;

public class Main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        int option = 0;
        String startDate;
        String endDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        FileHandler.setFileMenuLoop(fileHandler, scanner);

        do{
                Menu.displayMainMenu(); // displays the main menu
                option = Menu.getIntValue(scanner);

                System.out.println("Option selected: " + option);
            
            switch (option) {
                // case 1: add expense
                case 1:
                    // addExpenseHandler prompts the user for input and returns an expense object.
                    Expense expense = Expense.addExpenseHandler(scanner);
                    // writeExpenseToFile takes in the fileHandler and expnse object and writes the expense to the file.
                    fileHandler.writeExpenseToFile(fileHandler, expense);
                    break;
                case 2:
                    // readExpenseFromFile read from the file, and displays to standard output.
                    fileHandler.readExpenseFromFile(fileHandler);
                    break;
                case 3:
                    // If there's a nextInt(), nextDouble(), or any other nextXxx() method before nextLine()
                    // use an extra scanner.nextLine(); to consume the newline character.
                    
                    scanner.nextLine();
                    try {
                        do{
                            System.out.print("Please enter start date (dd-MM-yyyy):");
                            startDate = scanner.nextLine();

                            System.out.print("Please enter end date (dd-MM-yyyy):");
                            endDate = scanner.nextLine();
                        }while(!ExpenseReport.isStartDateValid(dateFormat.parse(startDate), dateFormat.parse(endDate)));
                        ExpenseReport.loadExpensesFromFile(fileHandler.getFileName(), dateFormat.parse(startDate), dateFormat.parse(endDate));

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    System.out.println(ExpenseReport.getExpenseMap().entrySet());
                    break;
                default:
                    break;
            }
        }while(option != 4);

        scanner.close();
    }
}


