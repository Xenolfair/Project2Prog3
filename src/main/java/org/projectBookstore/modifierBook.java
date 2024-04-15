package org.projectBookstore;
import java.util.Scanner;
public class modifierBook {
    public static void modifyBook(Book book, Scanner scanner) {
        System.out.println("\n--> What do you want to change? ");
        System.out.println("\n          1. Title");
        System.out.println("\n          2. Author");
        System.out.println("\n          3. ISBN");
        System.out.println("\n          4. Editorial");
        System.out.println("\n          5. Date of publication");
        System.out.println("\n          6. Value");

        int userElection2 = scanner.nextInt();
        scanner.nextLine();

        switch (userElection2) {
            case 1:
                System.out.println("Type the new title: ");
                String newTitle = scanner.nextLine();
                book.setTitle(newTitle);
                break;
            case 2:
                System.out.println("Type the new author: ");
                String newAuthor = scanner.nextLine();
                book.setAuthor(newAuthor);
                break;
            case 3:
                System.out.println("Type the new ISBN: ");
                String newIsbn = scanner.nextLine();
                book.setIsbn(newIsbn);
                break;
            case 4:
                System.out.println("Write the new editorial: ");
                String newEditorial = scanner.nextLine();
                book.setEditorial(newEditorial);
                break;
            case 5:
                System.out.println("Type the new publication date: ");
                int newYP = scanner.nextInt();
                book.setYearPublication(newYP);
                break;
            case 6:
                System.out.println("Type the new price: ");
                int newPrice = scanner.nextInt();
                book.setPrice(newPrice);
                break;
            default:
                System.out.println("Invalid option");
        }
    }
}
