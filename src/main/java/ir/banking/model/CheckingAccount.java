package src.main.java.ir.banking.model;

public class CheckingAccount extends  BankAccount{

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance, String mobileNumber) {
        super(accountNumber, ownerName, initialBalance, mobileNumber);
    }
}
