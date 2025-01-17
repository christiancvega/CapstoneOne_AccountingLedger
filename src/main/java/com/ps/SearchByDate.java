package com.ps;

import java.time.LocalDate;

import static com.ps.Main.*;

public class SearchByDate {

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
