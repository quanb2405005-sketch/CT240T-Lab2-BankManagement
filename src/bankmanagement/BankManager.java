package bankmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankManager {

    private Map<String, BankAccount> accounts;

    public BankManager() {
        accounts = new HashMap<>();
    }

    public void addAccount(BankAccount account) {

        if (account == null) {
            throw new IllegalArgumentException(
                    "Tai khoan khong duoc null."
            );
        }

        String accountNumber = account.getAccountNumber();

        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException(
                    "So tai khoan da ton tai."
            );
        }

        accounts.put(accountNumber, account);
    }

    public BankAccount findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void transferMoney(
            String fromAcc,
            String toAcc,
            double amount)
            throws InvalidAmountException,
            InsufficientBalanceException {

        if (fromAcc.equals(toAcc)) {
            throw new IllegalArgumentException(
                    "Khong the chuyen tien cho chinh minh."
            );
        }

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "So tien chuyen phai lon hon 0."
            );
        }

        BankAccount sender = findAccount(fromAcc);
        BankAccount receiver = findAccount(toAcc);

        if (sender == null) {
            throw new IllegalArgumentException(
                    "Khong tim thay tai khoan gui."
            );
        }

        if (receiver == null) {
            throw new IllegalArgumentException(
                    "Khong tim thay tai khoan nhan."
            );
        }

        sender.withdraw(amount);
        receiver.deposit(amount);
    }

    public double calculateTotalBalance(
            List<? extends BankAccount> accounts) {

        double total = 0;

        for (BankAccount account : accounts) {
            total += account.getBalance();
        }

        return total;
    }

    public List<BankAccount> getAllAccounts() {

        return new ArrayList<>(accounts.values());
    }

    public void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("Chua co tai khoan.");
            return;
        }

        for (BankAccount account : accounts.values()) {
            System.out.println(account);
        }
    }
}