package demo13;

public class Account {

    private double balance = 100000;

    public void deposit(double amount) throws IllegalArgumentException {
        if ( amount < 0 ) {
            throw new IllegalArgumentException("do not use negative values");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws IllegalArgumentException {
        if ( amount > 0 ) {
            throw new IllegalArgumentException("do not use positive values");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public static void transfer(Account acc1, Account acc2, double value) {
        acc1.withdraw(value);
        acc2.deposit(value);
    }
}
