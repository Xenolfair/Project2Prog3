package org.projectBookstore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Book {
    private User user;
    private String title;
    private String author;
    private String isbn;
    private String editorial;
    private int yearPublication;
    private double price;
    private boolean loaned = false;
    private String dateLoan;
    private String dateDevolution;

    // Constructor
    public Book(String title, String author, String isbn, String editorial, int yearPublication, double price, boolean loaned) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.editorial = editorial;
        this.yearPublication = yearPublication;
        this.price = price;
        this.loaned = loaned;
    }
    public boolean availableAt() {
        return !loaned;
    }

    public void lendBook(User user, String dateLoan, String dateDevolution) {
        if (availableAt()) {
            loaned = true;
            this.dateLoan = dateLoan;
            this.dateDevolution = dateDevolution;
            System.out.println("\nBook loaned to: " + user.getName());
            System.out.println("Loan date: " + dateLoan);
            System.out.println("Return date: " + dateDevolution);
        } else {
            System.out.println("\nThe book has already been borrowed");
        }
    }
    public int calculateDaysDelay(String dateReturnDateDear) {
        LocalDate dateCurrent = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dateDear = LocalDate.parse(dateReturnDateDear, formatter);
        long daysDelay = ChronoUnit.DAYS.between(dateDear, dateCurrent);
        return (int) Math.max(0, daysDelay);
    }

    public void returnBook() {
        loaned = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void showInfo() {
        System.out.println("------> " + title + ":\n");
        System.out.println("     Author: " + author + "\n");
        System.out.println("     ISBN: " + isbn + "\n");
        System.out.println("     Editorial: " + editorial + "\n");
        System.out.println("     Year of publication: " + yearPublication + "\n");
        System.out.println("     Value: $" + price + " COP \n");
        if (loaned) {
            System.out.println("     Loaned: Yes");
        } else {
            System.out.println("     Loaned: No");
        }
    }
}


