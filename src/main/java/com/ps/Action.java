package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.ps.Main.inputScanner;
import static com.ps.Main.transactions;

public class Action {

    // start creating my methods for all the commands
    public static void addDeposit() {
        //get the date and time for the deposit
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        //get a description for the deposit
        System.out.println("Describe the deposit: ");
        String description = inputScanner.nextLine();

        // ask for vendor name
        System.out.println("Vendor Name: ");
        String vendor = inputScanner.nextLine();

        // get the amount for the deposit
        System.out.println("Deposit Amount: ");
        double amount = inputScanner.nextDouble();

        // create a new transaction
        Transaction newTransaction = new Transaction(currentDate, currentTime, description, vendor, amount);
        transactions.add(newTransaction);

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            buffer.write(String.format("%s|%s|%s|%s|%.2f\n", currentDate, currentTime, description, vendor, amount));
            System.out.println("Deposit added successfully");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }

    public static void makePayment() {
        // get date and time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        // ask for the description
        System.out.println("Description for payment:");
        String description = inputScanner.nextLine();
        // ask for the vendor name
        System.out.println("Vendor: ");
        String vendor = inputScanner.nextLine();
        // ask for the amount of the payment
        System.out.println("Payment Amount: ");
        double amount = inputScanner.nextDouble();
        amount = -amount; // to ensure the payment is record as negative
        // create the new transaction
        Transaction newTransaction = new Transaction(currentDate, currentTime, description, vendor, amount);

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            buffer.write(String.format("%s|%s|%s|%s|%.2f%n", currentDate, currentTime, description, vendor, amount));
            System.out.println("Payment recorded successfully");
        } catch (IOException e) {
            System.out.println("Error Writing to transactions file" + e.getMessage());
        }


    }

}
