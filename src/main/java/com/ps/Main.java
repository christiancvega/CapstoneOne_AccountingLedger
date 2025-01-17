package com.ps;

import java.util.Scanner;

public class Main {


    static TransactionList transactions = new TransactionList();


    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {

        FileWriter.loadTransactions();


        DisplaySubMenu.displayMainMenu();
    }
}

