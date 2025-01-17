package com.ps;

public class FileWriter {

    public static void main(String[] args) {

        // *** Begin creating a menu

        // initialize the command variable to understand user input
        int mainMenuCommand;
        // load transactions from the csv files
        try {
            BufferedReader buffy = new BufferedReader(new FileReader("transactions.csv"));
            String header = buffy.readLine();
            String input;
            int nextEmptyIndex = 0;
            while ((input = buffy.readLine()) != null) {
                String[] transactionData = input.split("\\|");
                LocalDate date = LocalDate.parse(transactionData[0]);
                LocalTime time = LocalTime.parse(transactionData[1]);
                String description = transactionData[2];
                String vendor = transactionData[3];
                double amount = Double.parseDouble(transactionData[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);
            }
            buffy.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing transaction data: " + e.getMessage());
        }

    }}
