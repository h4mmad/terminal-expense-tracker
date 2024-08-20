import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

enum ExpenseType{
    Rent,
    Food,
    Transport,
    Bill,
    Subscription,
    Clothing,
    Other
}

public class Expense{
   
    private double amount;
    private ExpenseType type;
    private String description;
    private Date date;

    void setAmount(double amount){
        this.amount = amount;
    }
    double getAmount(){
        return this.amount;
    }

    void setType(ExpenseType type){
        this.type = type;
    }
    ExpenseType getType(){
        return this.type;
    }
    void setDescription(String description){
        this.description = description;
    }
    String getDescription(){
        return this.description;
    }
    void setDate(Date date){
        this.date = date;
    }
    Date getDate(){
        return this.date;
    }

    @Override
    public String toString() {
        return this.date + "," + this.amount + "," + this.type + "," + this.description;
    }

    public static void handleAddExpense(Scanner scanner, FileHandler fileHandler) {
        Expense expense = new Expense();
        
        System.out.print("Enter amount: ");
        expense.setAmount(scanner.nextDouble());
        
        int type = getExpenseType(scanner);
        expense.setType(getExpenseTypeEnum(type));

        scanner.next();
        System.out.print("Enter description: ");
        expense.setDescription(scanner.nextLine());
        expense.setDate(new Date());

        try (FileWriter fileWriter = new FileWriter(fileHandler.getFileName(), true)) {
            fileWriter.write(expense.toString() + "\n");
            System.out.println("Expense added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
    private static int getExpenseType(Scanner scanner) {
        int type;
        do {
            Menu.displayExpenseTypeMenu();
            System.out.print("Choose an option: ");
            type = scanner.nextInt();
            if (type < 1 || type > 6) {
                System.out.println("Invalid option selected!");
            }
        } while (type < 1 || type > 6);
        return type;
    }

    private static ExpenseType getExpenseTypeEnum(int type) {
        switch (type) {
            case 1: return ExpenseType.Rent;
            case 2: return ExpenseType.Food;
            case 3: return ExpenseType.Bill;
            case 4: return ExpenseType.Subscription;
            case 5: return ExpenseType.Clothing;
            case 6: return ExpenseType.Other;
            default: throw new IllegalArgumentException("Invalid type");
        }
    }    

    public static Expense addExpenseHandler(Scanner scanner){
        Expense expense = new Expense();

        expense.setAmount(Menu.getDoubleValue(scanner));
       
        // Clear input stream as nextDouble does not consume the \n character.
        scanner.nextLine();

        Menu.displayExpenseTypeMenu();
        expense.setType(getExpenseTypeEnum(Menu.getIntValue(scanner)));

        //Clear input stream as nextInt does not consume the \n character.
        scanner.nextLine();

        System.out.print("Enter description: ");
        expense.setDescription(scanner.nextLine());
        expense.setDate(new Date());

        return expense;
    }
}
