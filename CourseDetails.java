package studentdetails1;

import java.util.Scanner;

public class CourseDetails {
    static String[] courseTitles = new String[100];
    static String[] instructorNames = new String[100];
    static String[] durations = new String[100];
    static int courseCount = 0;

    // Method to add course details
    public static void addCourseDetails(Scanner input) {
        if (courseCount >= 100) {
            System.out.println("Course limit reached!");
            return;
        }

        System.out.print("Enter Course Title: ");
        courseTitles[courseCount] = input.nextLine();

        System.out.print("Enter Instructor Name: ");
        instructorNames[courseCount] = input.nextLine();

        System.out.print("Enter Duration (e.g., 3 months): ");
        durations[courseCount] = input.nextLine();

        courseCount++;
        System.out.println("Course added successfully!");
    }

    // Method to view all course details
    public static void viewAllCourses() {
        if (courseCount == 0) {
            System.out.println("No courses to display.");
            return;
        }

        System.out.println("\n--- Course List ---");
        for (int i = 0; i < courseCount; i++) {
            System.out.println("Course Title: " + courseTitles[i] +
                    ", Instructor: " + instructorNames[i] +
                    ", Duration: " + durations[i]);
        }
    }
}
