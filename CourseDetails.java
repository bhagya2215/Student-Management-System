package studentdetails1;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseDetails {
    // Dynamic lists to store course information
    static ArrayList<String> courseTitles = new ArrayList<>();
    static ArrayList<String> instructorNames = new ArrayList<>();
    static ArrayList<String> durations = new ArrayList<>();

    // Method to add a course
    public static void addCourseDetails(Scanner input) {
        System.out.print("Enter Course Title: ");
        String title = input.nextLine();
        courseTitles.add(title);

        System.out.print("Enter Instructor Name: ");
        String instructor = input.nextLine();
        instructorNames.add(instructor);

        System.out.print("Enter Duration (e.g., 3 months): ");
        String duration = input.nextLine();
        durations.add(duration);

        System.out.println("Course added successfully!");
    }

    // Method to display all courses
    public static void viewAllCourses() {
        if (courseTitles.isEmpty()) {
            System.out.println("No courses to display.");
            return;
        }

        System.out.println("\n--- Course List ---");
        for (int i = 0; i < courseTitles.size(); i++) {
            System.out.println("Course Title: " + courseTitles.get(i) +
                    ", Instructor: " + instructorNames.get(i) +
                    ", Duration: " + durations.get(i));
        }
    }
}
