import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseManager {

    ArrayList<Expense> expenses = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

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

}