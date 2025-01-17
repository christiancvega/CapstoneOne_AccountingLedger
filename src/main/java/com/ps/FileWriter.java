package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class FileWriter {


    public static void loadTransactions() {
        try (BufferedReader buffy = new BufferedReader(new FileReader("transactions.csv"))) {

            String header = buffy.readLine();
            String input;

            while ((input = buffy.readLine()) != null) {
                String[] transactionData = input.split("\\|");
                LocalDate date = LocalDate.parse(transactionData[0]);
                LocalTime time = LocalTime.parse(transactionData[1]);
                String description = transactionData[2];
                String vendor = transactionData[3];
                double amount = Double.parseDouble(transactionData[4]);


                Transaction transaction = new Transaction(date, time, description, vendor, amount);


                Main.transactions.add(transaction);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing transaction data: " + e.getMessage());
        }
    }
}