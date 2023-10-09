public class BankAccount {
    private int accountNo;
    private double balance;

    public BankAccount(int accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public BankAccount(int accountNo) {
        this.accountNo = accountNo;
        this.balance = 0.0;
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNo);
        System.out.println("Balance: $" + balance);
    }

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(101, 200000.0);
        BankAccount account2 = new BankAccount(102, 150000.0);

        System.out.println("Account 1 Details:");
        account1.displayAccountDetails();

        System.out.println("\nAccount 2 Details:");
        account2.displayAccountDetails();
    }
}
