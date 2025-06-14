package Loging;

import java.util.Scanner;

public class Loging {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Arrays to store usernames and passwords
        String[] usernames = {"admin", "user1", "student"};
        String[] passwords = {"1234", "pass1", "abc123"};

        int maxAttempts = 3;
        int attempts = 0;
        boolean loggedIn = false;
        String currentUser = "";

        System.out.println("=== Java Console Login System ===");

        // Login attempt loop
        while (attempts < maxAttempts && !loggedIn) {
            System.out.print("Enter username: ");
            String inputUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String inputPassword = scanner.nextLine();

            // Check username and password
            boolean found = false;
            for (int i = 0; i < usernames.length; i++) {
                if (inputUsername.equals(usernames[i]) && inputPassword.equals(passwords[i])) {
                    loggedIn = true;
                    currentUser = usernames[i];
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("\nLogin successful! Welcome, " + currentUser + "!");
            } else {
                attempts++;
                if (attempts < maxAttempts) {
                    System.out.println("Invalid credentials. Attempts left: " + (maxAttempts - attempts));
                } else {
                    System.out.println("Too many failed attempts. Access denied.");
                }
            }
        }

        // After login, show a simple switch menu
        if (loggedIn) {
            int option;
            do {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. View Profile");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Logged in as: " + currentUser);
                        break;
                    case 2:
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (option != 2);
        }

        scanner.close();
    }
}