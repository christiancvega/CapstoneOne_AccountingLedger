package com.ps;
//importing date and time tools

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


// Creating a transaction class and declaring my variables
// making sure to use local date and local time variables to store the date+time better
public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // Setting up my constructor and giving all the variables a value
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;


    }

    // Getters and Setters to call this information
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    // to string for clear info calling
// making sure to use date and time formatter to display those times clearly

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %.2f",
                time, date, description, vendor, amount);
    }
}



