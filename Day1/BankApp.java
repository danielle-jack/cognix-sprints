import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

interface AccountOperations {
    void withdraw(double amount);
    void deposit(double amount);
}

//abstract USER class
//determines the type of user and their permissions
abstract class User {
    private String username;
    private String password;

    //defaults
    public User() {
        this.username = "";
        this.password = "";
    }

    //received input
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


//abstract ACCOUNT class
//determines the type of account and their permissions
abstract class Account {
    private String accountId;
    private double balance;
    private String ownerUsername;

    public Account(String accountId, String ownerUsername, double initialBalance) {
        this.accountId = accountId;
        this.ownerUsername = ownerUsername;
        this.balance = initialBalance;
    }

    //getters -> return account information
    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }
}


//subclasses of USER class
class Admin extends User {
    public Admin(String username, String password) {
        super(username, password); //calls the parent
    }
}

class Customer extends User {
    private Set<String> accountIds; //set of account IDs associated with the customer

    public Customer(String username, String password) {
        super(username, password); //calls the parent
        this.accountIds = new TreeSet<>(); //initialize the set
    }

    public void addAccount(String accountId) {
        this.accountIds.add(accountId);
    }

    public Set<String> getAccountIds() {
        return accountIds;
    }
}


class SavingsAccount extends Account implements AccountOperations {
    public SavingsAccount(String accountId, String ownerUsername, double initialBalance) {
        super(accountId, ownerUsername, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
        System.out.println("Deposited $" + amount + " to Savings Account " + getAccountId());
    }

    //does not allow overdraft,so checks if the balance is sufficient before allowing withdrawal
    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrew $" + amount + " from Savings Account " + getAccountId());
        } else {
            System.out.println("Insufficient funds in Savings Account " + getAccountId());
        }
    }
}

class CheckingsAccount extends Account implements AccountOperations {
    private double overdraftLimit = -500.00;

    public CheckingsAccount(String accountId, String ownerUsername, double initialBalance) {
        super(accountId, ownerUsername, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
        System.out.println("Deposited $" + amount + " to Checkings Account " + getAccountId());
    }

    @Override
    public void withdraw(double amount) {
        if ((getBalance() - amount) >= overdraftLimit) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrew $" + amount + " from Checkings Account " + getAccountId());
        if ((getBalance() - amount) < 0) {
            System.out.println("Warning: Your account is in overdraft. Current balance: $" + getBalance());
        }
        } else {
            System.out.println("Withdrawal failed: Overdraft limit exceeded" + getAccountId());
        }
    }
}

// BANK class, stores user data
class Bank {
    Map<String, User> users = new HashMap<>();

    Map<String, Account> accounts = new LinkedHashMap<>();

    public void seedData() {
        String[] adminNames = {"admin1", "superadmin"};

        for (String name : adminNames) {
            users.put(name, new Admin(name, "adminpass"));
        }

        //seed customers
        Customer c1 = new Customer("customer1", "custpass1");
        Customer c2 = new Customer("customer2", "custpass2");
        users.put(c1.getUsername(), c1);
        users.put(c2.getUsername(), c2);

        //seed customer accounts
        createAccount(new SavingsAccount("SAV001", c1.getUsername(), 1000.00));
        createAccount(new CheckingsAccount("CHK001", c1.getUsername(), 500.00));
        createAccount(new SavingsAccount("SAV002", c2.getUsername(), 2500.00));
    }

    public void createAccount(Account acc) {
        accounts.put(acc.getAccountId(), acc);
        User u = users.get(acc.getOwnerUsername());

        if (u instanceof Customer) {
            Customer customer = (Customer) u;
            customer.addAccount(acc.getAccountId());
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        for (User u : users.values()) {
            if (u instanceof Customer) {
                customerList.add((Customer) u);
            }
        }
        return customerList;
    }

    public List<Account> getAllAccounts(){
        return new ArrayList<>(accounts.values());
    }
}