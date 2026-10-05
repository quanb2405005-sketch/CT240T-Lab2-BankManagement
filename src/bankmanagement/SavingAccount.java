package bankmanagement;

public class SavingAccount extends BankAccount {

    private double interestRate;

    private static final double MIN_BALANCE = 50000;

    public SavingAccount(String accountNumber,
                         String holderName,
                         double balance,
                         double interestRate) {

        super(accountNumber, holderName, balance);

        if (balance < MIN_BALANCE) {
            throw new IllegalArgumentException(
                    "Tai khoan tiet kiem phai co so du toi thieu 50000 VNĐ."
            );
        }

        if (interestRate < 0) {
            throw new IllegalArgumentException(
                    "Lai suat khong duoc am."
            );
        }

        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {

        if (interestRate < 0) {
            throw new IllegalArgumentException(
                    "Lai suat khong duoc am."
            );
        }

        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientBalanceException,
            InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "So tien rut phai lon hon 0."
            );
        }

        if (getBalance() < amount) {
            throw new InsufficientBalanceException(
                    "So du khong du de rut tien."
            );
        }

        double oldBalance = getBalance();
        double newBalance = oldBalance - amount;

        if (newBalance < MIN_BALANCE) {
            throw new InsufficientBalanceException(
                    "So du sau khi rut phai toi thieu 50000 VNĐ."
            );
        }

        setBalance(newBalance);

        if (getBalance() != oldBalance - amount) {
            throw new IllegalStateException(
                    "Vi pham post-condition."
            );
        }

        if (getBalance() < MIN_BALANCE) {
            throw new IllegalStateException(
                    "Vi pham invariant cua SavingAccount."
            );
        }
    }

    @Override
    public String toString() {

        return "SavingAccount{" +
                "accountNumber='" + getAccountNumber() + '\'' +
                ", holderName='" + getHolderName() + '\'' +
                ", balance=" + getBalance() +
                ", interestRate=" + interestRate +
                '}';
    }
}