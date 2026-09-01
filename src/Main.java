import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Function function = new Function();
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("       WELCOME TO THE LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=================================================");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    function.addStudent(scanner);
                    break;
                case "2":
                    System.out.println("case 2");
                    break;
                case "3":
                    function.printReceipt(scanner);
                    break;
                case "4":
                    System.out.println("Thank you for using the Student Enrollment System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
        scanner.close();
    }



    private static void printMenu() {
        System.out.println("\n--------------- MAIN MENU ---------------");
        System.out.println("1. Register a new Student");
        System.out.println("2. Enroll courses for Student");
        System.out.println("3. Print Receipt");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

}

class Student {
    private String studentId;
    private String program;
    private String fullName;
    private String yearAndSection;

    public Student(String studentId,String program, String fullName, String yearAndSection) {
        this.studentId = studentId;
        this.program = program;
        this.fullName = fullName;
        this.yearAndSection = yearAndSection;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getProgram() {
        return program;
    }

    public String getFullName() {
        return fullName;
    }

    public String getYearAndSection() {
        return yearAndSection;
    }
}


class Function {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Scanner scan) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== ADD A NEW STUDENT =====");

            System.out.print("Enter Student ID: ");
            String studentId = scan.nextLine().trim();

            System.out.print("Enter Full Name: ");
            String fullName = scan.nextLine().trim();

            System.out.println("Select Program: 1. BSCS | 2. BSIT | 3. BSEMC | 4. BSIS");
            System.out.print("Choice: ");

            int programChoice;
            try {
                programChoice = Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Please Try Again!");
                continue;
            }

            if (programChoice < 1 || programChoice > 4) {
                System.out.println("Invalid Please Try Again!");
                continue;
            }
            String[] programs = {"BSCS", "BSIT", "BSEMC", "BSIS"};
            String program = programs[programChoice - 1];

            System.out.println("Select Year & Section: 1. 1-A | 2. 1-B | 3. 2-A | 4. 2-B | 5. 3-A | 6. 3-B | 7. 4-A | 8. 4-B");
            System.out.print("Choice: ");

            int yearAndSectionChoice;
            try {
                yearAndSectionChoice = Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Please Try Again!\"");
                continue;
            }

            if (yearAndSectionChoice < 1 || yearAndSectionChoice > 8) {
                System.out.println("Invalid Please Try Again!\".");
                continue;
            }

            String[] sections = {"1-A", "1-B", "2-A", "2-B", "3-A", "3-B", "4-A", "4-B"};
            String yearAndSection = sections[yearAndSectionChoice - 1];

            Student newStudent = new Student(studentId, program, fullName, yearAndSection);
            students.add(newStudent);

            System.out.println("\nStudent " + fullName + " (" + studentId + ") registered successfully!");
            running = false;
        }
    }


    public void printReceipt(Scanner scanner){
        System.out.println("\n===== SELECT STUDENT IDs =====");
        if (students.isEmpty()) {
            System.out.println("No students registered yet!");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            Student store = students.get(i);
            System.out.println((i + 1) + ". ID: " + store.getStudentId() + " | Name: " + store.getFullName() + " | Program: " + store.getProgram());
        }
        System.out.println("==================================");



    }
}


