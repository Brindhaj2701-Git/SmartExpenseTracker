public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Smart Expense Tracker");

        Expense expense = new Expense(
                101,
                "Expense",
                "Food",
                250.00
        );

        expense.display();

    }

}