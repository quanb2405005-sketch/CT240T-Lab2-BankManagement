package bankmanagement;

public abstract class BankAccount {

    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber,
                       String holderName,
                       double balance) {

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "So tai khoan khong duoc de trong."
            );
        }

        if (holderName == null || holderName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Ten chu tai khoan khong duoc de trong."
            );
        }

        if (balance < 0) {
            throw new IllegalArgumentException(
                    "So du ban dau khong duoc am."
            );
        }

        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {

        if (balance < 0) {
            throw new IllegalArgumentException(
                    "So du khong duoc am."
            );
        }

        this.balance = balance;
    }

    /**
     * Nap tien.
     *
     * Pre-condition:
     * amount > 0
     *
     * Post-condition:
     * balance_new = balance_old + amount
     *
     * Invariant:
     * balance >= 0
     */
    public void deposit(double amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "So tien nap phai lon hon 0."
            );
        }

        double oldBalance = balance;

        balance += amount;

        if (balance != oldBalance + amount) {
            throw new IllegalStateException(
                    "Vi pham post-condition khi nap tien."
            );
        }

        if (balance < 0) {
            throw new IllegalStateException(
                    "Vi pham invariant."
            );
        }
    }

    /**
     * Rut tien.
     *
     * Pre-condition:
     * amount > 0
     * balance >= amount
     *
     * Post-condition:
     * balance_new = balance_old - amount
     *
     * Invariant:
     * balance >= 0
     */
    public abstract void withdraw(double amount)
            throws InsufficientBalanceException,
            InvalidAmountException;

    @Override
    public String toString() {

        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", holderName='" + holderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}