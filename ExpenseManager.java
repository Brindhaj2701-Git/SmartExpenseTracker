import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseManager {

    
    ArrayList<Expense> expenses = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    private final String FILE_NAME = "expenses.txt";

    // Add Income
    public void addIncome() {

        System.out.print("Enter Transaction ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Income Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Income Amount: ");
        double amount = sc.nextDouble();

        Expense income = new Expense(id, "Income", category, amount);

        expenses.add(income);
        saveToFile();

        System.out.println("\nIncome Added Successfully!");
    }

    // Add Expense
    public void addExpense() {

        System.out.print("Enter Transaction ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Expense Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Expense Amount: ");
        double amount = sc.nextDouble();

        Expense expense = new Expense(id, "Expense", category, amount);

        expenses.add(expense);
        saveToFile();

        System.out.println("\nExpense Added Successfully!");
    }

    public void viewTransactions() {

    if (expenses.isEmpty()) {
        System.out.println("\nNo Transactions Found!");
        return;
    }

    System.out.println("\n===== ALL TRANSACTIONS =====");

    for (Expense e : expenses) {
        e.display();
    }
   }

   public void searchTransaction() {

    System.out.print("Enter Transaction ID: ");
    int id = sc.nextInt();

    for (Expense e : expenses) {

        if (e.getTransactionId() == id) {

            System.out.println("\nTransaction Found!");
            e.display();
            return;
        }
    }

    System.out.println("\nTransaction Not Found!");

    }

    public void showBalance() {

    double income = 0;
    double expense = 0;

    for (Expense e : expenses) {

        if (e.getType().equalsIgnoreCase("Income")) {
            income += e.getAmount();
        } else {
            expense += e.getAmount();
        }

    }
    
    System.out.println("\n===== BALANCE SUMMARY =====");
    System.out.println("Total Income  : ₹" + income);
    System.out.println("Total Expense : ₹" + expense);
    System.out.println("Balance       : ₹" + (income - expense));

    }


    public void updateTransaction() {

    System.out.print("Enter Transaction ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    for (Expense e : expenses) {

        if (e.getTransactionId() == id) {

            System.out.print("Enter New Category: ");
            e.setCategory(sc.nextLine());

            System.out.print("Enter New Amount: ");
            e.setAmount(sc.nextDouble());

            saveToFile();

            System.out.println("\nTransaction Updated Successfully!");
            return;
        }

    }

    System.out.println("\nTransaction Not Found!");
    }

    public void deleteTransaction() {

    System.out.print("Enter Transaction ID: ");
    int id = sc.nextInt();

    for (int i = 0; i < expenses.size(); i++) {

        if (expenses.get(i).getTransactionId() == id) {

            expenses.remove(i);

            saveToFile();

            System.out.println("\nTransaction Deleted Successfully!");
            return;
        }
    }

    System.out.println("\nTransaction Not Found!");
}


    public void saveToFile() {

    try {

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));

        for (Expense e : expenses) {

            writer.write(
                    e.getTransactionId() + "," +
                    e.getType() + "," +
                    e.getCategory() + "," +
                    e.getAmount());

            writer.newLine();
        }

        writer.close();

    } catch (IOException e) {

        System.out.println("Error saving data!");

    }
    

    }

    public void loadFromFile() {
        expenses.clear();
    try {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",");

            int id = Integer.parseInt(data[0]);
            String type = data[1];
            String category = data[2];
            double amount = Double.parseDouble(data[3]);

            Expense expense = new Expense(id, type, category, amount);

            expenses.add(expense);

        }

        reader.close();

    } catch (IOException e) {

        System.out.println("Error loading data!");

    }

    }

    

}