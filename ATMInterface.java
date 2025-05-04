import java.util.Scanner;

//Represents a simple bank account with basic operation
class BankAccount {
    private double balance;
    
     //constructor to set an initial balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    //method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    //method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn: ₹" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    //method to check current balance 
    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + balance);
    }
}

//Main class to stimulate ATM interface
public class ATMInterface {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
	//create a bank account with an initial balance
        BankAccount userAccount = new BankAccount(10000);

        int choice;
        boolean exit = false;

        System.out.println("Welcome to the ATM!");

	//ATM menu loop
        while (!exit) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userAccount.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    userAccount.withdraw(withdrawAmount);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
