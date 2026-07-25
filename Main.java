import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExpenseManager manager = new ExpenseManager();

        int choice;

        do {

            System.out.println("\n===== SMART EXPENSE TRACKER =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    manager.addIncome();
                    break;

                case 2:
                    manager.addExpense();
                    break;

                case 3:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");

            }

        } while (choice != 3);

        sc.close();

    }

}