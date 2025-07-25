package src.main.java.ir.banking.service.imple;

import src.main.java.ir.banking.factory.BankAccountFactory;
import src.main.java.ir.banking.loggerObserver.api.TransactionObserver;
import src.main.java.ir.banking.model.AccountType;
import src.main.java.ir.banking.model.BankAccount;
import src.main.java.ir.banking.service.api.Transaction;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

    private final Map<String, BankAccount> accounts = new ConcurrentHashMap<>();
    private static volatile  Bank bankInstance;

    private Bank() {
    }

    public static Bank getInstance(){
        if (bankInstance == null) {
            synchronized (Bank.class) {
                if (bankInstance == null) {
                    bankInstance = new Bank();
                }
            }
        }
        return bankInstance;
    }

    public void createAccount(AccountType accountType , String accountNumber, String ownerName, double initialBalance, String mobileNumber , TransactionObserver transactionObserver) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("The account is duplicated");
            return ;
        }
        BankAccount account = BankAccountFactory.createAccount(accountType, accountNumber, ownerName, initialBalance, mobileNumber);
        account.addObserver(transactionObserver);
        accounts.put(accountNumber, account);
        System.out.println("account creat: " + accountNumber +  " " + ownerName +  " " + initialBalance);
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean strategyTransaction(Transaction transaction) {
        return transaction.execute(this);
    }

    public void displayAllBalances() {
        System.out.println("---- all Account ----");
        accounts.values().forEach(System.out::println);
        System.out.println("------------------------------");
    }
}
