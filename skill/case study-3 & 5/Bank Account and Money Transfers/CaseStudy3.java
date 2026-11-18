
import java.util.ArrayList;
import java.util.List;

class BankAccount {

    private String accountNumber;
    private double balance;
    private double overdraftLimit;
    private boolean frozen;
    private boolean closed;

    private List<String> transactions = new ArrayList<>();

    // Constructor
    public BankAccount(String accountNumber, double balance,
                       double overdraftLimit) {

        if (balance < 0 || overdraftLimit < 0)
            throw new IllegalArgumentException("Invalid amount");

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
    }

    // Deposit
    public void deposit(double amount) {
        checkAccount();

        if (amount <= 0)
            throw new IllegalArgumentException("Invalid deposit amount");

        balance += amount;
        transactions.add("Deposited: Rs." + amount);
    }

    // Withdraw
    public void withdraw(double amount) {
        checkAccount();

        if (amount <= 0)
            throw new IllegalArgumentException("Invalid withdrawal amount");

        if (balance - amount < -overdraftLimit)
            throw new IllegalArgumentException("Overdraft limit exceeded");

        balance -= amount;
        transactions.add("Withdrawn: Rs." + amount);
    }

    // Transfer
    public static void transfer(BankAccount from,
                                BankAccount to, double amount) {

        synchronized (BankAccount.class) {

            from.checkAccount();
            to.checkAccount();

            if (amount <= 0)
                throw new IllegalArgumentException("Invalid amount");

            if (from.balance - amount < -from.overdraftLimit)
                throw new IllegalArgumentException("Insufficient balance");

            // Update both accounts
            from.balance -= amount;
            to.balance += amount;

            from.transactions.add("Transferred: Rs." + amount);
            to.transactions.add("Received: Rs." + amount);
        }
    }

    // Freeze Account
    public void freezeAccount() {
        if (closed)
            throw new IllegalStateException("Account is closed");

        frozen = true;
        System.out.println("Account frozen");
    }

    // Unfreeze Account
    public void unfreezeAccount() {
        if (closed)
            throw new IllegalStateException("Account is closed");

        frozen = false;
        System.out.println("Account unfrozen");
    }

    // Close Account
    public void closeAccount() {
        if (balance != 0)
            throw new IllegalStateException("Balance must be zero");

        closed = true;
        System.out.println("Account closed");
    }

    // Account Validation
    private void checkAccount() {
        if (frozen)
            throw new IllegalStateException("Account is frozen");

        if (closed)
            throw new IllegalStateException("Account is closed");
    }

    // Display Balance
    public void displayBalance() {
        System.out.println(accountNumber +
                " Balance: Rs." + balance);
    }

    // Display Transaction History
    public void showTransactions() {
        System.out.println("Transaction History:");

        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

// Main Class
public class CaseStudy3 {

    public static void main(String[] args) {

        BankAccount account1 =
                new BankAccount("1001", 10000, 2000);

        BankAccount account2 =
                new BankAccount("1002", 5000, 1000);

        // Deposit
        account1.deposit(2000);

        // Withdraw
        account1.withdraw(1000);

        // Transfer
        BankAccount.transfer(account1, account2, 5000);

        // Display balances
        account1.displayBalance();
        account2.displayBalance();

        // Transaction History
        account1.showTransactions();
        account2.showTransactions();

        // Freeze Account
        account1.freezeAccount();

        try {
            account1.withdraw(500);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Unfreeze Account
        account1.unfreezeAccount();
    }
}
