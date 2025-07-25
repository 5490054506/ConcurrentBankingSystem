package src.main.java.ir.banking.loggerObserver.impl;


import src.main.java.ir.banking.loggerObserver.api.TransactionObserver;

public class TransactionSmsLogger implements TransactionObserver {

    @Override
    public void onTransaction(String accountNumber, String transactionType, double amount , String mobileNumber) {
        System.out.println("[SMS] Account : " + accountNumber + " mobileNumber : " + mobileNumber + " transactionType : " + transactionType + " amount : " + amount);
        //TODO CALL SMS WEBSERVICE
    }
}
