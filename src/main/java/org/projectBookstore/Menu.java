package org.projectBookstore;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    public static void showMenu(Bookstore library, User user, Scanner scanner) {
        boolean leave = false;

        while (!leave) {
            System.out.println("\n--> Options: ");
            System.out.println("\n          1. Search Book by Title");
            System.out.println("\n          2. Search Book by Author");
            System.out.println("\n          3. Show Inventory");
            System.out.println("\n          4. Make Loan");
            System.out.println("\n          5. Return Borrowed Book");
            System.out.println("\n          6. Leave");
            System.out.print("\n --> Choose an option: ");

            int userElection = scanner.nextInt();
            switch(userElection){
                case 1:
                    scanner.nextLine();
                    System.out.println("Enter the name of the title you wish to search for: ");
                    String title = scanner.nextLine();
                    library.searchBookByTitle(title, scanner);
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Enter the author's name: ");
                    String author = scanner.nextLine();
                    library.searchBookByAuthor(author, scanner);
                    break;
                case 3:
                    library.showInventory();
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("Enter the name of the title to be loaned: ");
                    String titleLoan = scanner.nextLine();
                    System.out.println("Enter the estimated number of days to return: ");
                    int diasDear = scanner.nextInt();
                    scanner.nextLine();
                    LocalDate dateDevolution = LocalDate.now().plusDays(diasDear);
                    System.out.println("The estimated return date is: " + dateDevolution.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    library.performLoan(titleLoan, user, LocalDate.now().toString(), dateDevolution.toString());
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.println("Enter the name of the title to be returned: ");
                    String titleReturn = scanner.nextLine();
                    System.out.println("Enter the current date (YYYY-MM-DD): ");
                    String dateCurrent = scanner.nextLine();
                    while (!Utilities.isValidDate(dateCurrent)) {
                        System.out.println("Invalid current date. Please enter a valid date (YYYY-MM-DD): ");
                        dateCurrent = scanner.nextLine();
                    }
                    int daysDelay = Utilities.calculateDaysDelay(library.getDateLoan(titleReturn), dateCurrent);
                    if (daysDelay > 0) {
                        double debt = daysDelay * 1000;
                        user.setDebt(debt);
                        System.out.println("The return of the book has been successfully registered. A debt of " + debt + " COP by " + daysDelay + " day(s) late");
                    } else {
                        System.out.println("The return of the book has been successfully registered");
                    }
                    library.returnBook(titleReturn);
                    break;
                case 6:
                    System.out.println("See you soon... ");
                    leave = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option");
            }
        }
    }
}