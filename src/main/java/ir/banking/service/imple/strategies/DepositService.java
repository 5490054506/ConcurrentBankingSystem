package src.main.java.ir.banking.service.imple.strategies;

import src.main.java.ir.banking.model.BankAccount;
import src.main.java.ir.banking.service.api.Transaction;
import src.main.java.ir.banking.service.imple.Bank;

public class DepositService implements Transaction {
    private final String accountNumber;
    private final double amount;

    public DepositService(String accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public boolean execute(Bank bank) {
        BankAccount account = bank.getAccount(accountNumber);
        if (account == null) {
            System.out.println("account not found for deposit");
            return false;
        }
        account.deposit(amount);
        return true;
    }
}
