package com.ps;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.ps.Main.transactions;

public class SearchByDate {


    private static void displayTransactionsInRange(LocalDate startDate, LocalDate endDate) {
        System.out.println("Transactions between " + startDate + " and " + endDate + ":");
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("-----------------------------------------------------------");

        for (Transaction transaction : transactions.getTransactions()) {
            if ((transaction.getDate().isAfter(startDate.minusDays(1)) || transaction.getDate().isEqual(startDate))
                    && (transaction.getDate().isBefore(endDate.plusDays(1)))) {
                System.out.println(transaction);
            }
        }
        System.out.println("-----------------------------------------------------------");
    }

    public static void displayMonthToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

        displayTransactionsInRange(firstDayOfMonth, currentDate);
    }

    public static void displayPreviousMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(currentDate.minusMonths(1).lengthOfMonth());

        displayTransactionsInRange(firstDayOfPreviousMonth, lastDayOfPreviousMonth);
    }

    public static void displayYearToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfYear = currentDate.withDayOfYear(1);

        displayTransactionsInRange(firstDayOfYear, currentDate);
    }

    public static void displayPreviousYear() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfPreviousYear = currentDate.minusYears(1).withDayOfYear(1);
        LocalDate lastDayOfPreviousYear = currentDate.minusYears(1).withDayOfYear(currentDate.minusYears(1).lengthOfYear());

        displayTransactionsInRange(firstDayOfPreviousYear, lastDayOfPreviousYear);
    }

    public static void searchByVendor() {
        System.out.println("Enter the vendor name:");
        String vendorName = Main.inputScanner.nextLine();

        System.out.println("Transactions for vendor: " + vendorName);
        System.out.println("Time, Date, Description, Vendor, Amount");
        System.out.println("-----------------------------------------------------------");

        for (Transaction transaction : transactions.getTransactions()) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(transaction);
            }
        }
        System.out.println("-----------------------------------------------------------");
    }
}
