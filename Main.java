import java.util.Scanner;


public class Main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        int option = 0;
        
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
                    ExpenseReport.loadExpensesFromFile(fileHandler.getFileName());
                    System.out.println(ExpenseReport.getExpenseMap().entrySet());
                    break;
                default:
                    break;
            }
        }while(option != 4);


        scanner.close();
    }

    
    
}


