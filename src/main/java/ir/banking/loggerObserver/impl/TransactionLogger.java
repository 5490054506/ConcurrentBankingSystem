package src.main.java.ir.banking.loggerObserver.impl;

import src.main.java.ir.banking.loggerObserver.api.TransactionObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TransactionLogger implements TransactionObserver {

    @Override
    public synchronized void onTransaction(String accountNumber, String transactionType, double amount, String mobileNumber) {
        String logFile = "src/main/resources/log.txt";
        try (FileWriter fw = new FileWriter(logFile, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(
                    "Account : " + accountNumber + " mobileNumber : " + mobileNumber + " transactionType : " + transactionType + " amount : " + amount
            );

        } catch (IOException e) {
            System.err.println("IOException log transaction: " + e.getMessage());
        }
    }


}
