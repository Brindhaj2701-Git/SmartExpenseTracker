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

}