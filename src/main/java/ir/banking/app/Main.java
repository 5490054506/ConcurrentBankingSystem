package src.main.java.ir.banking.app;

import src.main.java.ir.banking.loggerObserver.impl.TransactionLogger;
import src.main.java.ir.banking.loggerObserver.impl.TransactionSmsLogger;
import src.main.java.ir.banking.model.AccountType;
import src.main.java.ir.banking.service.imple.Bank;
import src.main.java.ir.banking.service.imple.strategies.DepositService;
import src.main.java.ir.banking.service.imple.strategies.TransferTransaction;
import src.main.java.ir.banking.service.imple.strategies.WithdrawService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);
    private static final Bank bank = Bank.getInstance();;
    public static void main(String[] args)  {



        bank.createAccount(AccountType.CHECKING , "1234", "ali", 3000000,"09130135702",  new TransactionLogger());
        bank.createAccount(AccountType.SAVINGS , "12345", "hasan", 1000000,"09916778927", new TransactionSmsLogger());
        bank.createAccount(AccountType.CHECKING , "12346", "kamal", 2000000,"09121112222", new TransactionSmsLogger());

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> bank.strategyTransaction(new DepositService("1234", 2000)));
            executor.execute(() -> bank.strategyTransaction(new WithdrawService("12345", 6000)));
            executor.execute(() -> bank.strategyTransaction(new TransferTransaction("12346","1234",1000)));
            executor.execute(() -> bank.strategyTransaction(new TransferTransaction("1234","12346",1500)));
            executor.execute(() -> bank.strategyTransaction(new TransferTransaction("12346","1234",1500)));
            executor.execute(() -> bank.strategyTransaction(new TransferTransaction("1234","12346",1500)));
        }
        try {
            bank.displayAllBalances();
            Thread.sleep(1000);
            bank.displayAllBalances();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}