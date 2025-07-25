package src.main.java.ir.banking.service.imple.strategies;

import src.main.java.ir.banking.model.BankAccount;
import src.main.java.ir.banking.service.api.Transaction;
import src.main.java.ir.banking.service.imple.Bank;


public class TransferTransaction implements Transaction {
    private final String fromAccount;
    private final String toAccount;
    private final double amount;

    public TransferTransaction(String fromAccount, String toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public boolean execute(Bank bank) {
        BankAccount from = bank.getAccount(fromAccount);
        BankAccount to = bank.getAccount(toAccount);


        if (from == null || to == null) {
            System.out.println("account not found for transfer");
            return false;
        }


        from = fromAccount.compareTo(toAccount) < 0 ? from : to;
        to = fromAccount.compareTo(toAccount) < 0 ? to : from;

        synchronized (from) {
                synchronized (to) {
                    if (from.getBalance() < amount) {
                        System.out.println("transfer failed balance < amount");
                        return false;
                    }
                    from.transferTo(to, amount);
                    return true;
                }
            }
        }
    }
