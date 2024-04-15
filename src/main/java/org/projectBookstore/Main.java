package org.projectBookstore;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bookstore library = new Bookstore();
        User userCurrent = null;
        boolean leave = false;

        System.out.println("Welcome to common library!");
        while (!leave) {
            System.out.println("\n--> Menu:");
            System.out.println("  1. Login");
            System.out.println("  2. Register new user");
            System.out.println("  3. Leave");
            System.out.print("\n--> Choose an option: ");

            int optionMenu = scanner.nextInt();
            scanner.nextLine();

            switch (optionMenu) {
                case 1:
                    System.out.print("\nEnter your username: ");
                    String nameUser = scanner.nextLine();
                    System.out.print("Enter your ID number: ");
                    String UserID = scanner.nextLine();
                    userCurrent = User.login(nameUser, UserID);
                    if (userCurrent != null) {
                        System.out.println("\nSuccessful login!");
                        Menu.showMenu(library, userCurrent, scanner);
                    } else {
                        System.out.println("\nIncorrect user name or ID. Please try again");
                    }
                    break;
                case 2:
                    userCurrent = User.registerUser();
                    if (userCurrent != null) {
                        if (userCurrent.getRank() == 2) {
                            Admin.showMenuAdmin(library, userCurrent, scanner);
                        } else {
                            Menu.showMenu(library, userCurrent, scanner);
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nSee you soon!");
                    leave = true;
                    break;
                default:
                    System.out.println("\nInvalid option. Please select a valid option from the menu");
            }
        }
    }
}
