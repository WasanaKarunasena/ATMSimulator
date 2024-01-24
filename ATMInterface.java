import java.util.Scanner;

public class ATMInterface {
    private BankAccount userAccount;

    public ATMInterface(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void OptionList() {
        System.out.println("\n*** ATM Options ***");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int option, double amount) {
        switch (option) {
            case 1:
                if (userAccount.withdraw(amount)) {
                    System.out.println("Your Withdrawal successful. Remaining balance: " + userAccount.getBalance());
                } else {
                    System.out.println("You have Insufficient funds. Withdrawal failed.");
                }
                break;
            case 2:
                userAccount.deposit(amount);
                System.out.println("Your Deposit successful. New balance: " + userAccount.getBalance());
                break;
            case 3:
                System.out.println("Current balance: " + userAccount.getBalance());
                break;
            case 4:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your initial account balance: ");
        double initialBalance = scanner.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATMInterface atm = new ATMInterface(userAccount);

        while (true) {
            atm.OptionList();
            System.out.print("Enter option 1 or 2 or 3 or 4: ");
            int option = scanner.nextInt();

            if (option == 4) {
                break;
            }

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();

            atm.performTransaction(option, amount);
        }

        scanner.close();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

