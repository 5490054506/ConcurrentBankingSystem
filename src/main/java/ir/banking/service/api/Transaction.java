package src.main.java.ir.banking.service.api;

import src.main.java.ir.banking.service.imple.Bank;

public interface Transaction {
    boolean execute(Bank bank);
}
