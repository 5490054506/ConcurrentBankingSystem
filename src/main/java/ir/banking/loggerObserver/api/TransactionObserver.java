package src.main.java.ir.banking.loggerObserver.api;

public interface TransactionObserver {

    void onTransaction(String accountNumber, String transactionType, double amount, String mobileNumber);

}
