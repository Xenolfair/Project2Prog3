package org.projectBookstore;

import java.util.HashMap;

public class Loan {
    private HashMap<String, Book> inventory;

    public Loan(HashMap<String, Book> inventory) {
        this.inventory = inventory;
    }

    public void performLoan(String titleBook, User user, String dateLoan, String dateDevolution) {
        if (inventory.containsKey(titleBook)) {
            Book book = inventory.get(titleBook);
            if (book.availableAt()) {
                book.lendBook(user, dateLoan, dateDevolution);
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
                System.out.println("Book '" + titleBook + "' returned correctly.");
            } else {
                System.out.println("The book '" + titleBook + "' is not currently on loan");
            }
        } else {
            System.out.println("A book with the title was not found: " + titleBook);
        }
    }
}
