import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.TimeZone;
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
        //dd-MM-yyyy
        startDate = start;
        endDate = end;
    }

    public void filterByStartAndEndDate(){

    }

    // Method to check if the start date is valid (i.e., it is not after the end date)
    public static boolean isStartDateValid(Date startDate, Date endDate){

    // Compare startDate with endDate
    // The compareTo method returns:
    // 0 if both dates are equal
    // A negative number if startDate is before endDate
    // A positive number if startDate is after endDate
    int val = startDate.compareTo(endDate);

    // If val > 0, startDate is after endDate, which is not valid
    if(val > 0){
        // Print an error message to the console
        System.out.println("Error: Start date cannot occur after end date");
        // Return false indicating the start date is not valid
        return false;
    }
    else
        // Return true indicating the start date is valid
        return true;
}


    public static void loadExpensesFromFile(String fileName, Date startDate, Date endDate) {
    // expect start and end date to have been validated.
    // this means they are parseable, in proper format (dd-MM-yyyy) and start date does not occur after end date.


    // logic to compare dates: 
    // if the date read is greater than or equal to start date, accept that line
    // if the date read is lesser than or equal to end date, accept that line


 
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    try {
                        Date expenseDate = dateFormat.parse(parts[0]);
                        
                        if(startDate.equals(expenseDate) || startDate.before(expenseDate) && endDate.equals(expenseDate) || endDate.after(expenseDate)){
                            
                            System.out.println(line);
                            Double amount = Double.parseDouble(parts[1]);
                            ExpenseType type = ExpenseType.valueOf(parts[2]);
    
                            // Sum the amounts for each ExpenseType
                            expenseMap.merge(type, amount, Double::sum);
                        }

                       
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid expense type or amount in file: " + line);
                    }
                    catch (ParseException e){
                        System.out.println("Parse exeception: " + line);
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}