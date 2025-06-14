package CourseDetails1;
import java.util.Scanner;

public class CourseDetails1 {

    // Arrays to store course details
    static String[] courseNames = new String[100];
    static String[] courseCodes = new String[100];
    static int[] courseCredits = new int[100];
    static int courseCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addCourse(input);
                    break;
                case 2:
                    viewCourses();
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);
    }

    // 1. Show menu method
    public static void showMenu() {
        System.out.println("\n==== Course Management System ====");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Exit");
    }

    // 2. Add course method
    public static void addCourse(Scanner input) {
        input.nextLine(); // clear buffer

        if (courseCount < courseNames.length) {
            System.out.print("Enter Course Name: ");
            courseNames[courseCount] = input.nextLine();

            System.out.print("Enter Course Code: ");
            courseCodes[courseCount] = input.nextLine();

            System.out.print("Enter Course Credits: ");
            courseCredits[courseCount] = input.nextInt();

            // Validate credits using if-else
            if (courseCredits[courseCount] <= 0) {
                System.out.println("Invalid credits. Must be positive.");
                courseCredits[courseCount] = 0;
            }

            courseCount++;
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Course list is full!");
        }
    }

    // 3. View courses method
    public static void viewCourses() {
        if (courseCount == 0) {
            System.out.println("No courses to show.");
        } else {
            System.out.println("\n--- Course List ---");
            for (int i = 0; i < courseCount; i++) {
                System.out.println((i + 1) + ". " + courseNames[i] + " | Code: " + courseCodes[i] + " | Credits: " + courseCredits[i]);
            }
        }
    }
}
