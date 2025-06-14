
package Score1;

import java.util.ArrayList;
import java.util.Scanner;

class Score1 {
    private String name;
    private int rollNumber;
    private ArrayList<Integer> scores;

    public Score1(String name, int rollNumber, ArrayList<Integer> scores) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.scores = new ArrayList<>(scores);
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public ArrayList<Integer> getScores() { return new ArrayList<>(scores); }

    public int getTotal() {
        return scores.stream().mapToInt(Integer::intValue).sum();
    }

    public double getAverage() {
        return scores.isEmpty() ? 0.0 : getTotal() / (double) scores.size();
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNumber);
        System.out.print("Scores: ");
        for (int i = 0; i < scores.size(); i++) {
            System.out.print("Subject " + (i + 1) + ": " + scores.get(i));
            if (i < scores.size() - 1) System.out.print(", ");
        }
        System.out.println();
        System.out.println("Total: " + getTotal());
        System.out.printf("Average: %.2f%n", getAverage());
    }
}

public class StudentMarksAdd {
    private static Scanner sc = new Scanner(System.in);
    public static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getValidIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addSingleStudent();
                    break;
                case 2:
                   
                    try {
                        Class<?> searchClass = Class.forName("StudentMarksSearch");
                        Object searchInstance = searchClass.newInstance();
                        searchClass.getMethod("runSearchMenu").invoke(searchInstance);
                    } catch (Exception e) {
                        System.out.println("Search functionality not available. Please ensure StudentMarksSearch.java is compiled.");
                        System.out.println("Press Enter to continue...");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using Student Marks Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1, 2, or 3.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== STUDENT MARKS MANAGEMENT ===");
        System.out.println("1. Add Student Scores");
        System.out.println("2. Search & View Marks Sheet");
        System.out.println("3. Exit");
        System.out.println("=================================");
    }

    private static void addSingleStudent() {
        System.out.println("\n--- ADD STUDENT SCORES ---");
        
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Please enter name: ");
            name = sc.nextLine().trim();
        }

        int roll = getValidIntInput("Roll Number: ");
        
        
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("Roll number already exists! Please enter a different roll number.");
                return;
            }
        }
        
        int subjectCount = getValidIntInput("Enter number of subjects: ");
        while (subjectCount <= 0) {
            subjectCount = getValidIntInput("Number of subjects must be positive. Enter again: ");
        }
        
        ArrayList<Integer> scores = new ArrayList<>();

        for (int j = 0; j < subjectCount; j++) {
            int score = getValidIntInput("Enter score for subject " + (j + 1) + " (0-100): ");
            while (score < 0 || score > 100) {
                score = getValidIntInput("Score must be between 0-100. Enter again: ");
            }
            scores.add(score);
        }

        students.add(new Student(name, roll, scores));
        
        System.out.println("\n✓ SUCCESS: Student record has been added successfully!");
        System.out.println("Student Details:");
        students.get(students.size() - 1).display();
        System.out.println("\nReturning to main menu...");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static int getValidIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            }
        }
    }

   
    public static ArrayList<Student> getStudents() {
        return students;
    }
}