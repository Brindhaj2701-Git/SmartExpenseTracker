public class Expense {

    // Variables
    private int transactionId;
    private String type;
    private String category;
    private double amount;

    // Constructor
    public Expense(int transactionId, String type, String category, double amount) {
        this.transactionId = transactionId;
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    // Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Display Method
    public void display() {
        System.out.println("-------------------------------");
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Type           : " + type);
        System.out.println("Category       : " + category);
        System.out.println("Amount         : ₹" + amount);
    }
}