package nl.inholland;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuItem {
    public static void displayStudents(List<Student> students) {
        System.out.println("LIST OF STUDENTS\n");
        System.out.printf(Student.STUDENT_FORMAT + "\n", "Id", "FirstName", "LastName", "Birthdate", "Age", "Group");
        System.out.printf(Student.STUDENT_FORMAT + "\n", "**", "*********", "********", "*********", "***", "*****");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    public static void displayTeachers(List<Teacher> teachers) {
        System.out.println("LIST OF TEACHERS\n");
        System.out.printf(Teacher.TEACHER_FORMAT + "\n", "Id", "FirstName", "LastName", "Birthdate", "Age");
        System.out.printf(Teacher.TEACHER_FORMAT + "\n", "**", "*********", "********", "*********", "***");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println();
    }

    public static void addStudent(Scanner in, DataInitializer initializer) throws FileNotFoundException {
        System.out.println("ADD STUDENT\n");

        System.out.print("Choose a username: ");
        String username = in.nextLine();

        System.out.print("Choose a password: ");
        String password = in.nextLine();

        System.out.print("Enter a first name: ");
        String firstName = in.nextLine();

        System.out.print("Enter a last name: ");
        String lastName = in.nextLine();

        LocalDate birthdate = LocalDate.now();
        boolean invalidDate = true;
        do {
            try {
                System.out.print("Please enter date of birth in MM/DD/YYYY: ");
                birthdate = LocalDate.parse(in.nextLine(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                invalidDate =  false;
            }
            catch (Exception e) {
                System.out.println("That was not a valid date, try again.\n");
            }
        }
        while (invalidDate);

        System.out.print("Enter group: ");
        String group = in.nextLine();

        if (initializer.addStudent(new Student(firstName, lastName, birthdate, group, username, password))) {
            initializer.initialize();
            System.out.println("The data was successfully added!\n");
        }
        else {
            System.out.println("Something went wrong while trying to add the new student, try again later.\n");
        }
    }

    public static void displayReports(DataInitializer initializer, Scanner in) throws FileNotFoundException {
        List<Integer> ids = new ArrayList<>();
        System.out.println("LIST OF STUDENT REPORTS\n");
        System.out.printf(Report.REPORT_FORMAT + "\n",
                "Id", "FirstName", "LastName", "Birthdate", "Age", "Group", "Java", "CSharp", "Python", "PHP");
        System.out.printf(Report.REPORT_FORMAT + "\n",
                "**", "*********", "********", "*********", "***", "*****", "****", "******", "******", "***");
        for (Student student : initializer.getStudents()) {
            System.out.println(initializer.getReportForStudent(student).showStudentWithGrades());
            ids.add(student.id);
        }
        System.out.println();

        int choice = enterChoice(ids, in);
        Student student;
        if (choice != 0) {
            for (Student s : initializer.getStudents()) {
                if (choice == s.id) {
                    student = s;
                    System.out.println(initializer.getReportForStudent(student));
                }
            }
        }
        System.out.println();
    }

    private static int enterChoice(List<Integer> choices, Scanner in) {
        // Keep asking for a choice until a valid one is given
        do {
            System.out.print("Enter student id (Report Details) | Or 0 back to main menu: ");
            String input = in.nextLine();

            if (tryParseInt(input)) {
                int choice = Integer.parseInt(input);

                if (choices.contains(choice) || choice == 0) {
                    return choice;
                }
            }

            // Show error message and let the loop continue
            System.out.println("Invalid choice, please try again.\n");
        }
        while (true);
    }

    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
