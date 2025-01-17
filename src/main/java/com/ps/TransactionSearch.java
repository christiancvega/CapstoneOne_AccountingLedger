package com.ps;

import static com.ps.Main.transactions;

public class TransactionSearch {

    public static void displayAll() {
        // create a header for transaction list
        System.out.println("All Transactions: ");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("---------------------------------------");

        // Loop through the array list to read out all of the transactions
        for (Transaction transaction : transactions.getTransactions()) {
            System.out.println(transaction);
        }

        System.out.println("---------------------------------------");
    }

    // Create a header for the display list
    public static void displayDeposits() {
        System.out.println("All Deposits");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("---------------------------------------");

        // Go through the array list and check for deposits
        for (Transaction transaction : transactions.getTransactions()) {
            // Search for positive transactions and print
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }

        System.out.println("---------------------------------------");
    }

    public static void displayPayments() {
        // Create the header for displaying payments
        System.out.println("All Payments: ");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("---------------------------------------");

        // Go through array list for deposits
        for (Transaction transaction : transactions.getTransactions()) {
            // If transaction is negative print
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }

        System.out.println("---------------------------------------");
    }
}
