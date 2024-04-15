package org.projectBookstore;

import java.util.HashMap;
import java.util.Scanner;

public class Bookstore {
    private HashMap<String, Book> inventory;
    private HashMap<String, String> loans;
    public Bookstore() {
        this.inventory = new HashMap<>();
        this.loans = new HashMap<>();
    }
    public void addBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter the book title: ");
        String title = scanner.nextLine();

        System.out.println("Enter the author of the book: ");
        String author = scanner.nextLine();

        System.out.println("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();

        System.out.println("Enter the publisher of the book: ");
        String editorial = scanner.nextLine();

        System.out.println("Enter the year of publication of the book: ");
        int yearPublication = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the price of the book: ");
        double price = Double.parseDouble(scanner.nextLine());

        boolean loaned = false;

        Book newBook = new Book(title, author, isbn, editorial, yearPublication, price, loaned);
        inventory.put(title, newBook);

        System.out.println("Book added to the inventory. There is " + inventory.size() + " book(s) in total\n");
    }

    public void searchBookByTitle(String title, Scanner scanner) {
        boolean found = false;

        for (Book book : inventory.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.showInfo();
                found = true;

                System.out.println("Do you wish to change anything? 1.(yes) / 2: ");
                int userElection = scanner.nextInt();
                if (userElection == 1) {
                    modifierBook.modifyBook(book, scanner);
                }
            }
        }
        if (!found) {
            System.out.println("A book with the title was not found: " + title);
        }
    }
    public void searchBookByAuthor(String author, Scanner scanner) {
        boolean found = false;

        for (Book book : inventory.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                book.showInfo();
                found = true;

                System.out.println("Do you wish to change anything? 1.(yes) / 2: ");
                int userElection = scanner.nextInt();
                if (userElection == 1) {
                    modifierBook.modifyBook(book, scanner);
                }
            }
        }
        if (!found) {
            System.out.println("No book was found with the author: " + author);
        }
    }

    public void showInventory(){
        for (Book book : inventory.values()) {
            book.showInfo();
        }
    }

    public void performLoan(String titleBook, User user, String dateLoan, String dateDevolution) {
        if (inventory.containsKey(titleBook)) {
            Book book = inventory.get(titleBook);
            if (book.availableAt()) {
                book.lendBook(user, dateLoan, dateDevolution);
                loans.put(titleBook, dateDevolution);
            } else {
                System.out.println("The book '" + titleBook + "' has already been borrowed");
            }
        } else {
            System.out.println("A book with the title was not found: " + titleBook);
        }
    }

    public void returnBook(String titleBook) {
        if (inventory.containsKey(titleBook)) {
            Book book = inventory.get(titleBook);
            if (book != null && !book.availableAt()) {
                book.returnBook();
                loans.remove(titleBook);
            } else {
                System.out.println("The book '" + titleBook + "' is not currently on loan");
            }
        } else {
            System.out.println("A book with the title was not found: " + titleBook);
        }
    }

    public String getDateLoan(String titleBook) {
        return loans.getOrDefault(titleBook, null);
    }
}
