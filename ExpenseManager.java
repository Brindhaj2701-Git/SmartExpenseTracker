import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class ExpenseManager {

    ArrayList<Expense> expenses = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    private final String FILE_NAME = "expenses.txt";

    // Add Income
    public void addIncome() {

        System.out.print("Enter Transaction ID: ");
        int id = sc.nextInt();

        if (transactionExists(id)) {
            System.out.println("Transaction ID already exists!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Income Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Income Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero!");
            return;
        }

        String date = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Expense income = new Expense(id, "Income", category, amount, date);

        expenses.add(income);
        saveToFile();

        System.out.println("\nIncome Added Successfully!");
    }

    // Add Expense
    public void addExpense() {

        System.out.print("Enter Transaction ID: ");
        int id = sc.nextInt();

        if (transactionExists(id)) {
            System.out.println("Transaction ID already exists!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Expense Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Expense Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero!");
            return;
        }

        String date = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Expense expense = new Expense(id, "Expense", category, amount, date);

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
                                e.getAmount() + "," +
                                e.getDate());

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
                String date = data[4];

                Expense expense = new Expense(id, type, category, amount, date);

                expenses.add(expense);

            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error loading data!");

        }

    }

    public boolean transactionExists(int id) {

        for (Expense e : expenses) {

            if (e.getTransactionId() == id) {
                return true;
            }

        }

        return false;
    }

    public void generateReport() {

        if (expenses.isEmpty()) {
            System.out.println("\nNo Transactions Found!");
            return;
        }

        double totalIncome = 0;
        double totalExpense = 0;

        double highestExpense = 0;
        double lowestExpense = Double.MAX_VALUE;

        int incomeCount = 0;
        int expenseCount = 0;

        for (Expense e : expenses) {

            if (e.getType().equalsIgnoreCase("Income")) {

                totalIncome += e.getAmount();
                incomeCount++;

            } else {

                totalExpense += e.getAmount();
                expenseCount++;

                if (e.getAmount() > highestExpense) {
                    highestExpense = e.getAmount();
                }

                if (e.getAmount() < lowestExpense) {
                    lowestExpense = e.getAmount();
                }
            }
        }

        if (expenseCount == 0) {
            lowestExpense = 0;
        }

        System.out.println("\n====================================");
        System.out.println("       FINANCIAL REPORT");
        System.out.println("====================================");

        System.out.println("Income Transactions  : " + incomeCount);
        System.out.println("Expense Transactions : " + expenseCount);
        System.out.println("Total Transactions   : " + expenses.size());

        System.out.println("------------------------------------");

        System.out.println("Total Income         : ₹" + totalIncome);
        System.out.println("Total Expense        : ₹" + totalExpense);
        System.out.println("Current Balance      : ₹" + (totalIncome - totalExpense));

        System.out.println("------------------------------------");

        System.out.println("Highest Expense      : ₹" + highestExpense);
        System.out.println("Lowest Expense       : ₹" + lowestExpense);

        System.out.println("====================================");
    }

    public void searchByCategory() {

        sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        boolean found = false;

        for (Expense e : expenses) {

            if (e.getCategory().equalsIgnoreCase(category)) {

                e.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo transactions found for this category!");
        }
    }

    public void searchByAmount() {

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        boolean found = false;

        for (Expense e : expenses) {

            if (e.getAmount() == amount) {

                e.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo transactions found with this amount!");
        }
    }

    public void viewIncomeTransactions() {

        boolean found = false;

        System.out.println("\n===== INCOME TRANSACTIONS =====");

        for (Expense e : expenses) {

            if (e.getType().equalsIgnoreCase("Income")) {

                e.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No income transactions found!");
        }
    }

    public void viewExpenseTransactions() {

        boolean found = false;

        System.out.println("\n===== EXPENSE TRANSACTIONS =====");

        for (Expense e : expenses) {

            if (e.getType().equalsIgnoreCase("Expense")) {

                e.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expense transactions found!");
        }
    }

    public void sortByAmount() {

        expenses.sort(Comparator.comparingDouble(Expense::getAmount));

        System.out.println("\nTransactions sorted by amount!");

        viewTransactions();
    }

    public void sortByTransactionId() {

        expenses.sort(Comparator.comparingInt(Expense::getTransactionId));

        System.out.println("\nTransactions sorted by Transaction ID!");

        viewTransactions();
    }
}