import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class StudentMarksSearch {
    private Scanner sc = new Scanner(System.in);

    public void runSearchMenu() {
        while (true) {
            showSearchMenu();
            int choice = getValidIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    searchAndViewMarksSheet();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                   
                    return;
                case 4:
                    System.out.println("Thank you for using Student Marks Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1, 2, 3, or 4.");
            }
        }
    }

    public static void main(String[] args) {
        StudentMarksSearch app = new StudentMarksSearch();
        app.runSearchMenu();
    }

    private void showSearchMenu() {
        System.out.println("\n=== STUDENT MARKS MANAGEMENT ===");
        System.out.println("1. Search & View Marks Sheet");
        System.out.println("2. View All Students");
        System.out.println("3. Back to Main Menu");
        System.out.println("4. Exit");
        System.out.println("=================================");
    }

    private void viewAllStudents() {
        try {
            Class<?> addClass = Class.forName("StudentMarksAdd");
            @SuppressWarnings("unchecked")
            ArrayList<Student> students = (ArrayList<Student>) addClass.getMethod("getStudents").invoke(null);
            
            if (students.isEmpty()) {
                System.out.println("\nNo student records found! Please add students first.");
                System.out.println("Press Enter to return to menu...");
                sc.nextLine();
                return;
            }

            System.out.println("\n--- ALL STUDENT RECORDS ---");
            System.out.println("Total Students: " + students.size());
            System.out.println("================================");
            
            for (int i = 0; i < students.size(); i++) {
                System.out.println("\nStudent " + (i + 1) + ":");
                students.get(i).display();
                System.out.println("--------------------------------");
            }
            
            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error accessing student data: " + e.getMessage());
        }
    }

    private void searchAndViewMarksSheet() {
        try {
            Class<?> addClass = Class.forName("StudentMarksAdd");
            @SuppressWarnings("unchecked")
            ArrayList<Student> students = (ArrayList<Student>) addClass.getMethod("getStudents").invoke(null);
            
            if (students.isEmpty()) {
                System.out.println("\nNo student records found! Please add students first.");
                System.out.println("Press Enter to return to menu...");
                sc.nextLine();
                return;
            }

            System.out.println("\n--- SEARCH & VIEW MARKS SHEET ---");
            System.out.println("1. Search by Student ID (Roll Number)");
            System.out.println("2. Search by Student Name");
            System.out.println("3. Back to Search Menu");
            
            int searchChoice = getValidIntInput("Enter search option: ");

            if (searchChoice == 3) {
                return; 
            }

            Student foundStudent = null;

            if (searchChoice == 1) {
                int searchRoll = getValidIntInput("Enter Roll Number: ");
                foundStudent = students.stream()
                    .filter(s -> s.getRollNumber() == searchRoll)
                    .findFirst()
                    .orElse(null);
            } else if (searchChoice == 2) {
                System.out.print("Enter Student Name: ");
                String searchName = sc.nextLine().trim();
                foundStudent = students.stream()
                    .filter(s -> s.getName().equalsIgnoreCase(searchName))
                    .findFirst()
                    .orElse(null);
            } else {
                System.out.println("Invalid option!");
                return;
            }

            if (foundStudent != null) {
                System.out.println("\n--- STUDENT MARKS SHEET ---");
                foundStudent.display();
                
                System.out.println("\nOptions:");
                System.out.println("1. Download as JPG");
                System.out.println("2. Back to Search Menu");
                
                int option = getValidIntInput("Enter option: ");
                if (option == 1) {
                    generateMarksSheet(foundStudent);
                }
            } else {
                System.out.println("\nStudent not found!");
                System.out.println("Press Enter to continue...");
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error accessing student data: " + e.getMessage());
        }
    }

    private void generateMarksSheet(Student student) {
        try {
            BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, 800, 600);
            
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(20, 20, 760, 560);
            
            g2d.setColor(new Color(0, 102, 204));
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            FontMetrics fm = g2d.getFontMetrics();
            String title = "STUDENT MARKS SHEET";
            int titleX = (800 - fm.stringWidth(title)) / 2;
            g2d.drawString(title, titleX, 80);
            
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(titleX, 90, titleX + fm.stringWidth(title), 90);
            
            int y = 150;
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            
            g2d.drawString("Student Name: " + student.getName(), 60, y);
            y += 40;
            g2d.drawString("Roll Number: " + student.getRollNumber(), 60, y);
            y += 60;
            
            g2d.setFont(new Font("Arial", Font.BOLD, 18));
            g2d.drawString("Subject-wise Scores:", 60, y);
            y += 40;
            
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            g2d.drawString("Subject", 80, y);
            g2d.drawString("Score", 300, y);
            g2d.drawString("Grade", 450, y);
            
            g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(60, y + 10, 600, y + 10);
            y += 30;
            
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            ArrayList<Integer> scores = student.getScores();
            for (int i = 0; i < scores.size(); i++) {
                String subject = "Subject " + (i + 1);
                int score = scores.get(i);
                String grade = getGrade(score);
                
                g2d.drawString(subject, 80, y);
                g2d.drawString(String.valueOf(score), 300, y);
                g2d.drawString(grade, 450, y);
                y += 25;
            }
            
            y += 30;
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(60, y, 600, y);
            y += 40;
            
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.drawString("Total Marks: " + student.getTotal(), 80, y);
            y += 30;
            g2d.drawString("Average: " + String.format("%.2f", student.getAverage()) + "%", 80, y);
            y += 30;
            g2d.drawString("Overall Grade: " + getGrade((int)student.getAverage()), 80, y);
            
            y = 550;
            g2d.setFont(new Font("Arial", Font.ITALIC, 12));
            g2d.setColor(Color.GRAY);
            g2d.drawString("Generated by Student Marks Management System", 60, y);
            g2d.drawString("Date: " + java.time.LocalDate.now(), 500, y);
            
            g2d.dispose();
            
            String fileName = "MarksSheet_" + student.getName().replaceAll("\\s+", "") + "" + student.getRollNumber() + ".jpg";
            File outputFile = new File(fileName);
            ImageIO.write(image, "jpg", outputFile);
            
            System.out.println("\n✓ SUCCESS: Marks sheet downloaded as: " + fileName);
            System.out.println("File saved in project directory.");
            System.out.println("Press Enter to continue...");
            sc.nextLine();
            
        } catch (IOException e) {
            System.out.println("Error generating marks sheet: " + e.getMessage());
            System.out.println("Press Enter to continue...");
            sc.nextLine();
        }
    }
    
    private String getGrade(int score) {
        if (score >= 90) return "A+";
        else if (score >= 80) return "A";
        else if (score >= 70) return "B+";
        else if (score >= 60) return "B";
        else if (score >= 50) return "C";
        else if (score >= 40) return "D";
        else return "F";
    }

    private int getValidIntInput(String prompt) {
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
}
