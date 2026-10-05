package bankmanagement;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner =
            new Scanner(System.in);

    private static BankManager bankManager =
            new BankManager();

    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();

            try {

                choice = Integer.parseInt(
                        scanner.nextLine()
                );

                switch (choice) {

                    case 1:
                        createAccount();
                        break;

                    case 2:
                        deposit();
                        break;

                    case 3:
                        withdraw();
                        break;

                    case 4:
                        transfer();
                        break;

                    case 5:
                        findAccount();
                        break;

                    case 6:
                        bankManager.displayAllAccounts();
                        break;

                    case 7:
                        calculateTotalBalance();
                        break;

                    case 0:
                        System.out.println(
                                "Da thoat chuong trinh."
                        );
                        break;

                    default:
                        System.out.println(
                                "Lua chon khong hop le."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Vui long nhap so."
                );

                choice = -1;

            } catch (Exception e) {

                System.out.println(
                        "Loi: " + e.getMessage()
                );

                choice = -1;
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println();
        System.out.println("==============================");
        System.out.println(" BANK MANAGEMENT SYSTEM");
        System.out.println("==============================");
        System.out.println("1. Tao tai khoan");
        System.out.println("2. Nap tien");
        System.out.println("3. Rut tien");
        System.out.println("4. Chuyen tien");
        System.out.println("5. Tim tai khoan");
        System.out.println("6. Hien thi tai khoan");
        System.out.println("7. Tong so du");
        System.out.println("0. Thoat");
        System.out.println("==============================");
        System.out.print("Chon: ");
    }

    private static void createAccount() {

        try {

            System.out.print("So tai khoan: ");
            String accountNumber =
                    scanner.nextLine();

            System.out.print("Ten chu tai khoan: ");
            String holderName =
                    scanner.nextLine();

            System.out.print("So du ban dau: ");
            double balance =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

            System.out.print("Lai suat (%): ");
            double interestRate =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

            SavingAccount account =
                    new SavingAccount(
                            accountNumber,
                            holderName,
                            balance,
                            interestRate
                    );

            bankManager.addAccount(account);

            System.out.println(
                    "Tao tai khoan thanh cong!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Loi: " + e.getMessage()
            );
        }
    }

    private static void deposit() {

        try {

            System.out.print("So tai khoan: ");
            String accountNumber =
                    scanner.nextLine();

            BankAccount account =
                    bankManager.findAccount(
                            accountNumber
                    );

            if (account == null) {
                System.out.println(
                        "Khong tim thay tai khoan."
                );
                return;
            }

            System.out.print("So tien nap: ");
            double amount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

            account.deposit(amount);

            System.out.println(
                    "Nap tien thanh cong."
            );

            System.out.println(
                    "So du moi: "
                            + account.getBalance()
            );

        } catch (Exception e) {

            System.out.println(
                    "Loi: " + e.getMessage()
            );
        }
    }

    private static void withdraw() {

        try {

            System.out.print("So tai khoan: ");
            String accountNumber =
                    scanner.nextLine();

            BankAccount account =
                    bankManager.findAccount(
                            accountNumber
                    );

            if (account == null) {
                System.out.println(
                        "Khong tim thay tai khoan."
                );
                return;
            }

            System.out.print("So tien rut: ");
            double amount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

            account.withdraw(amount);

            System.out.println(
                    "Rut tien thanh cong."
            );

            System.out.println(
                    "So du moi: "
                            + account.getBalance()
            );

        } catch (Exception e) {

            System.out.println(
                    "Loi: " + e.getMessage()
            );
        }
    }

    private static void transfer() {

        try {

            System.out.print("Tai khoan gui: ");
            String from =
                    scanner.nextLine();

            System.out.print("Tai khoan nhan: ");
            String to =
                    scanner.nextLine();

            System.out.print("So tien: ");
            double amount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

            bankManager.transferMoney(
                    from,
                    to,
                    amount
            );

            System.out.println(
                    "Chuyen tien thanh cong."
            );

        } catch (Exception e) {

            System.out.println(
                    "Loi: " + e.getMessage()
            );
        }
    }

    private static void findAccount() {

        System.out.print(
                "Nhap so tai khoan: "
        );

        String accountNumber =
                scanner.nextLine();

        BankAccount account =
                bankManager.findAccount(
                        accountNumber
                );

        if (account == null) {

            System.out.println(
                    "Khong tim thay tai khoan."
            );

        } else {

            System.out.println(account);
        }
    }

    private static void calculateTotalBalance() {

        List<BankAccount> accounts =
                bankManager.getAllAccounts();

        double total =
                bankManager.calculateTotalBalance(
                        accounts
                );

        System.out.println(
                "Tong so du: "
                        + total + " VNĐ"
        );
    }
}