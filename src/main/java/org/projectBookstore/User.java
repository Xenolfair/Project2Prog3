package org.projectBookstore;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String name;
    private String IDCard;
    private static int rank;
    private double debt;
    public static ArrayList<User> usersRegistered = new ArrayList<>();

    public User(String name, String IDCard, int rank) {
        this.name = name;
        this.IDCard = IDCard;
        this.rank = rank;
        usersRegistered.add(this);
    }
    public static User login(String nameUser, String IDCardUser) {
        for (User user : usersRegistered) {
            if (user.getName().equals(nameUser) && user.getIDCard().equals(IDCardUser)) {
                return user;
            }
        }
        return null;
    }

    public static User registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the user registration system");

        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Now, enter your ID number: ");
        String IDCard = scanner.nextLine();

        System.out.print("Also, enter your rank (1)User, (2)Admin: ");
        int rank = scanner.nextInt();
        scanner.nextLine();

        User user = new User(name, IDCard, rank);

        System.out.println("\nSuccessful registration!");
        System.out.println("--> User data:");
        System.out.println("Name: " + user.getName());
        System.out.println("ID number: " + user.getIDCard());
        System.out.println("Rank: " + (user.getRank() == 2 ? "Admin" : "user"));

        return user;
    }
    public static void showUsersRegistered() {
        if (usersRegistered.isEmpty()) {
            System.out.println("There are no registered users");
        } else {
            for (User user : usersRegistered) {
                System.out.println("Name: " + user.getName() + ", ID number: " + user.getIDCard() + ", Rank: " + (user.getRank() == 2 ? "Admin" : "User"));
            }
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public static int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }
}
