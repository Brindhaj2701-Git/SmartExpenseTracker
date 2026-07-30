import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExpenseManager manager = new ExpenseManager();
        manager.loadFromFile();

        int choice;

        do {

            System.out.println("\n===== SMART EXPENSE TRACKER =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Search Transaction");
            System.out.println("5. Show Balance");
            System.out.println("6. Update Transaction");
            System.out.println("7. Delete Transaction");
            System.out.println("8. Exit");

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
                    manager.viewTransactions();
                    break;

                case 4:
                    manager.searchTransaction();
                    break;

                case 5:
                    manager.showBalance();
                    break;

                case 6:
                    manager.updateTransaction();
                    break;

                case 7:
                    manager.deleteTransaction();
                    break;

                case 8:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");

            }

        } while (choice != 8);

        sc.close();
    }
}