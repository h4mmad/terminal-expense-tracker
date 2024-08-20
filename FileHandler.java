import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class FileHandler{
    private String fileName;
    private boolean fileExists;
   
    void setFileName(String fileName) throws IOException{
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("File does not exist: " + fileName);
        }

        this.fileName = fileName;
    }


    static void setFileMenuLoop(FileHandler fileHandler, Scanner scanner){
        do{
            System.out.println("Please type in the file name you want to read from: ");
            
            try{
                // fileHandler.setFileName(scanner.nextLine());

                //For testing purposes.
                fileHandler.setFileName("expenses.txt");
                fileHandler.setFileExists(true);
            }catch(IOException e){
                System.err.println("Error setting file name: " + e.getMessage());
                fileHandler.setFileExists(false);
            }
        }while(!fileHandler.getFileExists());
    }

    String getFileName(){
        return this.fileName;
    }
    public void setFileExists(boolean bool){
        this.fileExists = bool;
    }
    boolean getFileExists(){
        return this.fileExists;
    }

    void writeExpenseToFile(FileHandler fileHandler, Expense expense){
        try (FileWriter fileWriter = new FileWriter(fileHandler.getFileName(), true)) {
            fileWriter.write(expense.toString() + "\n");
            System.out.println("Expense added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readExpenseFromFile(FileHandler fileHandler){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileHandler.getFileName()))) {
                    String line;
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    while ((line = reader.readLine()) != null) {
                            Expense readExpense = new Expense();
                            String[] parts = line.split(","); // Split the line by comma
                            readExpense.setDate(formatter.parse(parts[0]));
                            readExpense.setAmount(Double.parseDouble(parts[1]));
                            readExpense.setType(ExpenseType.valueOf(parts[2]));
                            readExpense.setDescription(parts[3]);
                            System.out.println("Date: " + readExpense.getDate() + ", Amount: " + readExpense.getAmount() + ", Type: " + readExpense.getType() + ", Description: " + readExpense.getDescription());
                        }
                        
                    } catch (IOException e) {
                        System.out.println("An error occurred while reading the file.");
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
    }
}