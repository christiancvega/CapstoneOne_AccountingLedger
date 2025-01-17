package com.ps;

import static com.ps.Main.commandScanner;
import static com.ps.SearchByDate.*;
import static com.ps.TransactionSearch.*;

public class DisplaySubMenu {

    // create the main menu method
    public static void displayMainMenu() {
        int mainMenuCommand;

        do {
            // display the main menu
            System.out.println("-----------------------------------------------------------");
            System.out.println("Please enter an option: ");
            System.out.println("1) Add Deposit ");
            System.out.println("2) Make Payment (Debit) ");
            System.out.println("3) Ledger ");
            System.out.println("0) Exit ");
            System.out.println("-----------------------------------------------------------");
            System.out.print("Command: ");

            // get input from user
            mainMenuCommand = commandScanner.nextInt();

            // switch to handle the options
            switch (mainMenuCommand) {
                case 1:
                    ActionMethods.addDeposit();
                    break;
                case 2:
                    ActionMethods.makePayment();
                    break;
                case 3:
                    displaySubMenu();
                    break;
                case 0:
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("Command not found, please try again");
            }
        } while (mainMenuCommand != 0);
    }

    // create the sub menu with the ledger options
    public static void displaySubMenu() {
        int subMenuCommand;

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
            System.out.print("Command: ");

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
                    break;
                default:
                    System.out.println("Command not found. Try Again");
            }
        } while (subMenuCommand != 0);
    }

    // create method for reports menu (subsubmenu)
    public static void displayReportsMenu() {
        int reportsCommand;

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
            System.out.print("Command: ");
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
                    break;
                case 0:
                    System.out.println("Back to the ledger ");
                    break;
                default:
                    System.out.println("Command not found. Try Again");
            }
        } while (reportsCommand != 0);
    }
}

