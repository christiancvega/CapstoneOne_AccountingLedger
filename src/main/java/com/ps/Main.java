package com.ps;
//import my scanner and necessary apps

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Main {

    // create my array list for transactions
    static ArrayList<Transaction> transactions = new ArrayList<>();

    //create my scanners to recieve user input
    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);


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

        // do
        do {
            // Create a do whole loop for the main menu

            //main menu goes here (sout)
            System.out.println("-----------------------------------------------------------");
            System.out.println("Please enter an option: ");
            System.out.println("1) Add Deposit ");
            System.out.println("2) Make Payment (Debit) ");
            System.out.println("3) Ledger ");
            System.out.println("0) Exit ");
            System.out.println("-----------------------------------------------------------");
            System.out.println("Command: ");
            //try
            try {
                // import the scanner and recieve input as a variable
                mainMenuCommand = commandScanner.nextInt();
                // catch
            } catch (InputMismatchException ime) {
                ime.printStackTrace();
                mainMenuCommand = 0;
            }
            //switch statements to match the options provided to the user
            switch (mainMenuCommand) {
                //create case/break for each option so we can call the corresponding method
                case 1:
                    addDeposit();
                    break;
                case 2:
                    makePayment();
                    break;
                case 3:
                    displaySubMenu();
                    break;
                case 0:
                    System.out.println("Exiting ...");
                    break;
                // make sure to be able to handle invalid commands
                default:
                    System.out.println("Command not found, please try again");

            }
            // while command is not 0
        } while (mainMenuCommand != 0);
// ** End Main Menu
    }

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

    // create the sub menu with the ledger options
    public static void displaySubMenu() {
        // create variable to receive command
        int subMenuCommand;
        // do
        do {
            // display the sub menu
            System.out.println("-----------------------------------------------------------");
            System.out.println("Please enter an option: ");
            System.out.println("1) All ");
            System.out.println("2) Deposits ");
            System.out.println("3) Payments ");
            System.out.println("4) Reports ");
            System.out.println("0) Home ");
            System.out.println("-----------------------------------------------------------");
            System.out.println("Command: ");

            // get input from user
            subMenuCommand = commandScanner.nextInt();

            // switch statements to connect to our options
            switch (subMenuCommand) {
                case 1:
                    displayAll();
                    break;
                case 2:
                    displayDeposits();
                    break;
                case 3:
                    displayPayments();
                    break;
                case 4:
                    displayReportsMenu();
                    break;
                case 0:
                    System.out.println("Going back to the main menu... ");
                default:
                    System.out.println("Command not found. Try Again");
            }
        } while (subMenuCommand != 0);
    }

    // methods to connect to our switch cases
    public static void displayAll() {
        //create a header for transaction list
        System.out.println("All Transactions: ");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("---------------------------------------");
        //Loop through the array list to read out all of the transactions
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("---------------------------------------");
    }

    // create a header for the display list
    public static void displayDeposits() {
        System.out.println("All Deposits");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("---------------------------------------");

// go through array list and check for deposits
        for (Transaction transaction : transactions) {
            // search for positive transactions and print
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
        System.out.println("---------------------------------------");
    }

    public static void displayPayments() {
        // create the header for displaying payments
        System.out.println("All Payments: ");
        System.out.println("Time. Date, Description, Vendor, Amount");
        System.out.println("---------------------------------------");

        // go through array list for desposits
        for (Transaction transaction : transactions) {
            // if transaction is negative print
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
        System.out.println("---------------------------------------");

    }

    // create method for reports menu (subsubmenu)
    public static void displayReportsMenu() {
        // initialize command
        int reportsCommand;
// create a do while loop
        do {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Please enter an option: ");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.println("-----------------------------------------------------------");
            System.out.println("Command: ");
            // get input from user
            reportsCommand = commandScanner.nextInt();

            switch (reportsCommand) {
                case 1:
                    displayMonthToDate();
                    break;
                case 2:
                    displayPreviousMonth();
                    break;
                case 3:
                    displayYearToDate();
                    break;
                case 4:
                    displayPreviousYear();
                    break;
                case 5:
                    searchByVendor();
                case 0:
                    System.out.println("Back to the ledger ");
                    break;
                default:
                    System.out.println("Command not found. Try Again");
            }


        } while (reportsCommand != 0);
    }

    // methods to connect to my cases
    public static void displayMonthToDate() {
        //Get the current date first and get first day of the month
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

        System.out.println("This month's transactions: ");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("---------------------------------------");



        for (Transaction transaction : transactions) {
            if ((transaction.getDate().isAfter(firstDayOfMonth.minusDays(1)) || transaction.getDate().isEqual(firstDayOfMonth))
                    && transaction.getDate().isBefore(currentDate.plusDays(1))) {
                System.out.println(transaction);

            }


        }
        System.out.println("---------------------------------------");

    }

    public static void displayPreviousMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(currentDate.minusMonths(1).lengthOfMonth());

        System.out.println("Transactions for the previous month:");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("-----------------------------------------------------------");



        for (Transaction transaction : transactions) {
            if ((transaction.getDate().isAfter(firstDayOfPreviousMonth.minusDays(1)) || transaction.getDate().isEqual(firstDayOfPreviousMonth))
                    && (transaction.getDate().isBefore(lastDayOfPreviousMonth.plusDays(1)))) {
                System.out.println(transaction);

            }
        }

        System.out.println("-----------------------------------------------------------");
    }

    public static void displayYearToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfYear = currentDate.withDayOfYear(1);

        System.out.println("Transactions for the current year:");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("-----------------------------------------------------------");



        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(firstDayOfYear.minusDays(1)) && transaction.getDate().isBefore(currentDate.plusDays(1))) {
                System.out.println(transaction);

            }
        }
        System.out.println("-----------------------------------------------------------");
    }

    public static void displayPreviousYear() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfPreviousYear = currentDate.minusYears(1).withDayOfYear(1);
        LocalDate lastDayOfPreviousYear = currentDate.minusYears(1).withDayOfYear(currentDate.minusYears(1).lengthOfYear());

        System.out.println("Transactions for the previous year:");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("-----------------------------------------------------------");


        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(firstDayOfPreviousYear.minusDays(1)) && transaction.getDate().isBefore(lastDayOfPreviousYear.plusDays(1))) {
                System.out.println(transaction);
            }
        }
        System.out.println("-----------------------------------------------------------");
    }
    public static void searchByVendor() {
        commandScanner.nextLine();

        System.out.println("Enter the vendor name:");
        String vendorName = inputScanner.nextLine();

        System.out.println("Transactions for vendor: " + vendorName);
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("-----------------------------------------------------------");

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(transaction);
            }
        }
        System.out.println("-----------------------------------------------------------");
    }
}