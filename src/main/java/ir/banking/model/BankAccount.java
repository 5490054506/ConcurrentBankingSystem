package src.main.java.ir.banking.model;

import src.main.java.ir.banking.loggerObserver.api.TransactionObserver;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private final String accountNumber;
    private final String ownerName;
    private double balance;
    private String mobileNumber;
    private final List<TransactionObserver> observers = new ArrayList<>();

    public BankAccount(String accountNumber, String ownerName, double initialBalance, String mobileNumber) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.mobileNumber = mobileNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public synchronized double getBalance() {
        return balance;
    }


    public synchronized void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }


    private synchronized void notifyObservers(String transactionType, double amount) {
        for (TransactionObserver obs : observers) {
            obs.onTransaction(accountNumber, transactionType, amount, mobileNumber);
        }
    }

    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("invalid transfer amount for deposit");
            return;
        }
        balance += amount;

        notifyObservers("deposit", amount);
    }

    public synchronized boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("invalid transfer amount for withdraw");
            return false;
        }
        balance -= amount;

        notifyObservers("withdraw", amount);
        return true;
    }

    public void transferTo(BankAccount target, double amount) {

        if (amount <= 0) {
            System.out.println("invalid transfer amount");
            return;
        }

        synchronized (this) {
            if (balance < amount) {
                System.out.println("transfer failed balance < amount");
                return;
            }
            balance -= amount;
        }
        synchronized (target) {
            target.balance += amount;
        }
        notifyObservers("transfer to", amount);
        target.notifyObservers("transfer in", amount);
    }


    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
