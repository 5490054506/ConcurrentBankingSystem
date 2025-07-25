package src.main.java.ir.banking.factory;

import src.main.java.ir.banking.model.AccountType;
import src.main.java.ir.banking.model.BankAccount;
import src.main.java.ir.banking.model.CheckingAccount;
import src.main.java.ir.banking.model.SavingsAccount;

public class BankAccountFactory {

    public static BankAccount createAccount(AccountType type, String accountNumber, String ownerName, double initialBalance, String mobileNumber) {
        BankAccount bankAccount = null;
        switch (type) {
            case SAVINGS:
                bankAccount = new SavingsAccount(accountNumber, ownerName, initialBalance, mobileNumber);
                break;
            case CHECKING:
                bankAccount = new CheckingAccount(accountNumber, ownerName, initialBalance, mobileNumber);
        }

        return bankAccount;
    }
}
