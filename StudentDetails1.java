/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdetails1;

import java.util.Scanner;

/**
 *
 * @author DHANE
 */
public class StudentDetails1 {

        // Arrays to store student data
    static int[] Ids = new int[100];
    static String[] FirstName = new String[100];
    static String[] LastName = new String[100];
    static String[] Area = new String[100];
    static String[] BirthDate = new String[100];
    static String[] Gender = new String[100];
    static int studentCount = 0;

    public static void main(String[] args) {
        
        
        Scanner input = new Scanner(System.in);
        int choice;

        // Control Panel Loop
        while (true) {
            System.out.println("\n------- Student Control Panel -------");
            System.out.println("1. Add Student Details");
            System.out.println("2. View All Students Details");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent(input);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent(input);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method 1: Add Student Details
    public static void addStudent(Scanner input) {
        if (studentCount >= 100) {
            System.out.println("Student limit reached!");
            return;
        }

        System.out.print("Enter Student ID: ");
        Ids[studentCount] = input.nextInt();
        input.nextLine(); // consume newline

        System.out.print("Enter First Name: ");
        FirstName[studentCount] = input.nextLine();

        System.out.print("Enter Last Name: ");
        LastName[studentCount] = input.nextLine();

        System.out.print("Enter Area: ");
        Area[studentCount] = input.nextLine();

        System.out.print("Enter Birthdate (dd/mm/yyyy): ");
        BirthDate[studentCount] = input.nextLine();

        System.out.print("Enter Gender (Male/Female): ");
        Gender[studentCount] = input.nextLine();

        studentCount++;
        System.out.println("Student added successfully!");
    }

    // Method 2: View All Students Details
    public static void viewStudents() {
        if (studentCount == 0) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("ID: " + Ids[i] + ", Name: " + FirstName[i] + " " + LastName[i] +
                    ", Area: " + Area[i] + ", Birthdate: " + BirthDate[i] + ", Gender: " + Gender[i]);
        }
    }

    // Method 3: Search Student by ID
    public static void searchStudent(Scanner input) {
        if (studentCount == 0) {
            System.out.println("No students available to search.");
            return;
        }

        System.out.print("Enter ID to search: ");
        int searchId = input.nextInt();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (Ids[i] == searchId) {
                System.out.println("Student Found!");
                System.out.println("ID: " + Ids[i] + ", Name: " + FirstName[i] + " " + LastName[i] +
                        ", Area: " + Area[i] + ", BirthDate: " + BirthDate[i] + ", Gender: " + Gender[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + searchId + " not found.");
        }

        
        
    }
    
}
