import java.util.Scanner;

public class Runner {
    private static Bank bank = new Bank();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Bank App");
        bank.seedData();
        
        //get login information from user
        System.out.println("Please enter your username:");
        String username = sc.nextLine();
        System.out.println("Please enter your password:");
        String password = sc.nextLine();

        User loggedInUser = bank.users.get(username);
        //if successful login
        if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
            //check if user is admin or customer
            if (loggedInUser instanceof Admin) {
                adminDashboard((Admin) loggedInUser);
            } else if (loggedInUser instanceof Customer) {
                customerDashboard((Customer) loggedInUser);
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
        
        System.out.println("Thank you for using the Bank App.");
        sc.close();
}

//ADMIN DASHBOARD
private static void adminDashboard(Admin admin) {
    boolean exit = false;
    while (!exit) {
        System.out.println("Admin Dashboard");
        System.out.println("1. View all customers");
        System.out.println("2. View all accounts");
        System.out.println("3. Exit");
        System.out.println("Please select an option: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1: //show customer usernames
                System.out.println("All Customers:");
                for (Customer c : bank.getAllCustomers()) {
                    System.out.println("- " + c.getUsername());
                }
                break;
            case 2: //show account IDs and balances
                System.out.println("All Accounts:");
                for (Account a : bank.getAllAccounts()) {
                    System.out.println("ID: " + a.getAccountId() + " | Balance: " + a.getBalance());
                }
                break;
            case 3:
                exit = true;
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

//CUSTOMER DASHBOARD
private static void customerDashboard(Customer customer) {
    boolean exit = false;
    while (!exit) {
        System.out.println("Customer Dashboard");
        System.out.println("1. View my accounts");
        System.out.println("2. Deposit to an account");
        System.out.println("3. Withdraw from an account");
        System.out.println("4. Exit");
        System.out.println("Please select an option: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Your Accounts:");
                for (String accId : customer.getAccountIds()) {
                    Account a = bank.accounts.get(accId);
                    System.out.println("ID: " + a.getAccountId() + " | Balance: " + a.getBalance());
                }
                break;
            case 2:
                processTransaction(customer);
                break;
            case 3:
                exit = true;
                break;
            default:
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //Transaction Logic
    private static void processTransaction(Customer customer) {
        System.out.println("Enter the account ID:");
        String accId = sc.nextLine();
        
        if (!customer.getAccountIds().contains(accId)) {
            System.out.println("You do not own this account.");
            return;
        }

        Account acc = bank.accounts.get(accId);
        System.out.println("Please select transaction type:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        int transactionType = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter the amount:");
        try { //block to catch invalid input
            double amount = Double.parseDouble(sc.nextLine());

            if (acc instanceof AccountOperations) {
                AccountOperations accOps = (AccountOperations) acc;
                if (transactionType == 1) {
                    accOps.deposit(amount);
                } else if (transactionType == 2) {
                    accOps.withdraw(amount);
                } else {
                    System.out.println("Invalid transaction type.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

}

