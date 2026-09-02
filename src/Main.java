import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Function function = new Function();
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("       WELCOME TO THE STUDENT ENROLLMENT SYSTEM");
        System.out.println("=================================================");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    function.addStudent(scanner);
                    break;
                case "2":
                    function.addCourses(scanner);
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
        System.out.println("--------------- MAIN MENU ---------------");
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
    private ArrayList<String> enrolledCourses;

    public Student(String studentId,String program, String fullName, String yearAndSection) {
        this.studentId = studentId;
        this.program = program;
        this.fullName = fullName;
        this.yearAndSection = yearAndSection;
        this.enrolledCourses = new ArrayList<>();
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

    public void addCourse(String course) {
        this.enrolledCourses.add(course);
    }

    public String getYearAndSection() {
        return yearAndSection;
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }
}


class Function {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Scanner scan) {
        boolean running = true;

        while (running) {
            System.out.println("===== ADD A NEW STUDENT =====");

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

            System.out.println("Student " + fullName + " (" + studentId + ") registered successfully!");
            running = false;
        }
    }

    public void addCourses(Scanner scanner){
        ArrayList<String> courses = new ArrayList<>();
        boolean select = true;

        System.out.println("===== SELECT STUDENT IDs =====");
        if (students.isEmpty()) {
            System.out.println("No students registered yet!");
            return;
        }

        System.out.println("===== REGISTERED STUDENTS =====");
        for (Student studentStore : students) {
            System.out.println("ID: " + studentStore.getStudentId() + " | Name: " + studentStore.getFullName());
        }
        System.out.println("===============================");

        Student selectedStudent = null;
        while (selectedStudent == null) {
            System.out.print("Enter Student ID to add courses for or '0' to cancel: ");
            String searchId = scanner.nextLine().trim();

            if (searchId.equals("0")) {
                return;
            }

            for (Student s : students) {
                if (s.getStudentId().equalsIgnoreCase(searchId)) {
                    selectedStudent = s;
                    break;
                }
            }
            if (selectedStudent == null) {
                System.out.println("Invalid Please Try Again");
            }
        }

        System.out.println("\nSelected Student: " + selectedStudent.getFullName() + " (" + selectedStudent.getStudentId() + ")");

        while (select) {
            System.out.println("========================================");
            System.out.println("           SELECT COURSES");
            System.out.println("========================================");
            System.out.println("1. Add Courses");
            System.out.println("2. Exit");
            System.out.println("Select an option: ");

            int choice = -1;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Please Try Again");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                boolean validCount = true;
                int coursesNumber = 0;

                while (validCount){
                    System.out.println("How many Courses?");
                    try{coursesNumber = scanner.nextInt();
                        scanner.nextLine();
                        if (coursesNumber <= 0) {
                            System.out.println("Invalid Please Try Again");
                        } else {
                            validCount = false;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Please Try Again");
                        scanner.nextLine();
                    }
                }
                for (int i = 0; i < coursesNumber; i++) {
                    String courseNumber = "";
                    boolean validCourse = true;

                    while (validCourse) {
                        System.out.print("Enter course #" + (i + 1) + ": ");
                        courseNumber = scanner.nextLine().trim();

                        if (courseNumber.isEmpty()) {
                            System.out.println("Invalid Please Try Again");
                        }
                        else if (courseNumber.matches("-?\\d+")) {
                            System.out.println("Invalid Please Try Again");
                        }
                        else if (!courseNumber.matches(".*[a-zA-Z0-9].*")) {
                            System.out.println("Invalid Please Try Again");
                        }
                        else {
                            validCourse = false;
                        }
                    }
                    selectedStudent.addCourse(courseNumber);
                }
                System.out.println("Saved Courses: " + courses);

            } else if (choice == 2) {
                select = false;
            } else {
                System.out.println("Invalid option. Try again.");
            }

        }
    }


    public void printReceipt(Scanner scanner) {
        System.out.println("===== SELECT STUDENT IDs =====");
        if (students.isEmpty()) {
            System.out.println("No students registered yet!");
            return;
        }

        for (Student s : students) {
            System.out.println("ID: " + s.getStudentId() + " | Name: " + s.getFullName());
        }

        Student selectedStudent = null;
        while (selectedStudent == null) {
            System.out.print("\nEnter Student ID to print receipt or '0' to cancel: ");
            String searchId = scanner.nextLine().trim();

            if (searchId.isEmpty()) {
                continue;
            }

            if (searchId.equals("0")) {
                return;
            }

            for (Student s : students) {
                if (s.getStudentId().equalsIgnoreCase(searchId)) {
                    selectedStudent = s;
                    break;
                }
            }
            if (selectedStudent == null) {
                System.out.println("Invalid Please Try Again");
            }
        }

        System.out.println("==================================");
        System.out.println("         OFFICIAL RECEIPT         ");
        System.out.println("==================================");
        System.out.println("ID: " + selectedStudent.getStudentId());
        System.out.println("Name: " + selectedStudent.getFullName());
        System.out.println("Program: " + selectedStudent.getProgram());
        System.out.println("Year & Section: " + selectedStudent.getYearAndSection());
        System.out.println("----------------------------------");
        System.out.println("ENROLLED COURSES:");

        ArrayList<String> courses = selectedStudent.getEnrolledCourses();
        if (courses.isEmpty()) {
            System.out.println("  No courses enrolled.");
        } else {
            int courseIndex = 1;
            for (String course : courses) {
                System.out.println("  Course #" + courseIndex + ": " + course);
                courseIndex++;
            }
        }
        System.out.println("==================================");
    }
}


