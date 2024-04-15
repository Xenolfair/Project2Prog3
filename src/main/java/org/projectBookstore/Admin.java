package org.projectBookstore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Admin {
    public static void showMenuAdmin(Bookstore library, User user, Scanner scanner) {
        boolean leave = false;

        while (!leave) {
            System.out.println("\n--> Options: ");
            System.out.println("\n          1. Add New Book");
            System.out.println("\n          2. Search Book by Title");
            System.out.println("\n          3. Search Book by Author");
            System.out.println("\n          4. Show Inventory");
            System.out.println("\n          5. Make Loan");
            System.out.println("\n          6. Return Borrowed Book");
            System.out.println("\n          7. View user data");
            System.out.println("\n          8. Modify credentials of registered users");
            System.out.println("\n          9. exit");
            System.out.print("\n --> Choose an option: ");

            int userElection = scanner.nextInt();
            switch(userElection){
                case 1:
                    library.addBook();
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Enter the name of the title to search for: ");
                    String title = scanner.nextLine();

                    library.searchBookByTitle(title, scanner);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Enter the author's name: ");
                    String author = scanner.nextLine();

                    library.searchBookByAuthor(author, scanner);
                    break;
                case 4:
                    library.showInventory();
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.println("Enter the name of the title to lend: ");
                    String titleLoan = scanner.nextLine();

                    System.out.println("Enter the estimated number of days to return: ");
                    int daysDear = scanner.nextInt();

                    scanner.nextLine();
                    LocalDate dateDevolution = LocalDate.now().plusDays(daysDear);
                    System.out.println("The estimated return date is: " + dateDevolution.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    library.performLoan(titleLoan, user, LocalDate.now().toString(), dateDevolution.toString());
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.println("Enter the name of the title to be returned: ");
                    String titleReturn = scanner.nextLine();

                    System.out.println("Enter the current date (YYYYY-MM-DD): ");
                    String dateCurrent = scanner.nextLine();

                    while (!Utilities.isValidDate(dateCurrent)) {
                        System.out.println("Invalid current date. Please enter a valid date (YYYYY-MM-DD): ");
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
                case 7:
                    System.out.println("--> Registered Users:");
                    User.showUsersRegistered();
                    break;
                case 8:
                    if (User.usersRegistered.isEmpty()) {
                        System.out.println("There are no registered users to modify");
                    } else {
                        scanner.nextLine();
                        System.out.print("Enter the name of the user to be modified: ");
                        String usernameModify = scanner.nextLine();

                        boolean userFound = false;
                        for (User userRegistered : User.usersRegistered) {
                            if (userRegistered.getName().equalsIgnoreCase(usernameModify)) {
                                System.out.print("Enter the new name for the user: ");
                                String newName = scanner.nextLine();

                                System.out.print("Enter the new ID for the user: ");
                                String newIDCard = scanner.nextLine();

                                System.out.print("Enter the new rank for the user (1)User, (2)Admin: ");
                                int newRank = scanner.nextInt();

                                userRegistered.setName(newName);
                                userRegistered.setIDCard(newIDCard);
                                userRegistered.setRank(newRank);

                                System.out.println("User credentials modified correctly");
                                userFound = true;
                                break;
                            }
                        }
                        if (!userFound) {
                            System.out.println("No user with the provided name was found");
                        }
                    }
                    break;
                case 9:
                    System.out.println("See you soon...");
                    leave = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option");
            }
        }
    }
}
