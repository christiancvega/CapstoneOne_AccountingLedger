package com.ps;
import static com.ps.DisplaySubMenu.displaySubMenu;
import static com.ps.TransactionSearch.*;
import static com.ps.SearchByDate.*;
import static com.ps.Action.*;

//import my scanner and necessary apps

import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Main {

    // create my array list for transactions
    static ArrayList<Transaction> transactions = new ArrayList<>();

    //create my scanners to recieve user input
    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);




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
// ** End Main com.ps.Main.Menu
    }
    }
