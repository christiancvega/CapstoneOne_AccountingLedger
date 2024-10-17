package com.ps;
//import my scanner and necessary apps

import java.io.*;
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
                float amount = Float.parseFloat(transactionData[4]);

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
            System.out.println("Please enter an option");
            System.out.println("1) Add Deposit ");
            System.out.println("2) Make Payment (Debit) ");
            System.out.println("3) Ledger ");
            System.out.println("0) Exit ");
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
        System.out.println("Command for adding a deposit");
    }

    public static void makePayment() {
        System.out.println("Command for adding a payment");
    }

    // create the sub menu with the ledger options
    public static void displaySubMenu() {
        // create variable to receive command
        int subMenuCommand;
        // do
        do {
            // display the sub menu
            System.out.println("Please enter an option: ");
            System.out.println("1) All ");
            System.out.println("2) Deposits ");
            System.out.println("3) Payments ");
            System.out.println("4) Reports ");
            System.out.println("0) Home ");
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
        System.out.println("Display all transactions");
    }

    public static void displayDeposits() {
        System.out.println("Display all deposits");
    }

    public static void displayPayments() {
        System.out.println("Display all payments");
    }

    // create method for reports menu (subsubmenu)
    public static void displayReportsMenu() {
        // initialize command
        int reportsCommand;
// create a do while loop
        do {
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
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
        System.out.println("Display month to date");
    }

    public static void displayPreviousMonth() {
        System.out.println("Display previous month");
    }

    public static void displayYearToDate() {
        System.out.println("Dislay year to date");
    }

    public static void displayPreviousYear() {
        System.out.println("Display previous year");
    }

    public static void searchByVendor() {
        System.out.println("Search by vendor");
    }
}