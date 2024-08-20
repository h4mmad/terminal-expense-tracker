import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public class ExpenseReport {

    private static Map<ExpenseType, Double> highestExpense;
    private static Date startDate;
    private static Date endDate;
    
    private static Map<ExpenseType, Double> expenseMap = new EnumMap<>(ExpenseType.class);

    public static  Map<ExpenseType, Double> getExpenseMap(){
        return expenseMap;
    }

    public static Map<ExpenseType, Double> getHighestExpense(){
        return highestExpense;
    }

    public void setStartAndEndDate(Date start, Date end){
        startDate = start;
        endDate = end;
    }


    public static void loadExpensesFromFile(String fileName) {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    try {
                        Double amount = Double.parseDouble(parts[1]);
                        ExpenseType type = ExpenseType.valueOf(parts[2]);

                        // Sum the amounts for each ExpenseType
                        expenseMap.merge(type, amount, Double::sum);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid expense type or amount in file: " + line);
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

}