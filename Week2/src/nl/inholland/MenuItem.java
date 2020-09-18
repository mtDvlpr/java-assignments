package nl.inholland;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuItem {
    public static void displayStudents(List<Student> students) {
        System.out.println("LIST OF STUDENTS\n");
        System.out.printf(Student.STUDENT_FORMAT, "Id", "FirstName", "LastName", "Birthdate", "Age", "Group");
        System.out.printf(Student.STUDENT_FORMAT, "**", "*********", "********", "*********", "***", "*****");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    public static void displayTeachers(List<Teacher> teachers) {
        System.out.println("LIST OF TEACHERS\n");
        System.out.printf(Teacher.TEACHER_FORMAT, "Id", "FirstName", "LastName", "Birthdate", "Age");
        System.out.printf(Teacher.TEACHER_FORMAT, "**", "*********", "********", "*********", "***");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println();
    }

    public static void addStudent(Scanner in, DataInitializer initializer) {
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
                System.out.println("That was not a valid date, try again.");
                System.out.println();
            }
        }
        while (invalidDate);

        System.out.print("Enter group: ");
        String group = in.nextLine();

        if (initializer.addStudent(new Student(firstName, lastName, birthdate, group, username, password))) {
            System.out.println("The data was successfully added!\n");
        }
        else {
            System.out.println("Something went wrong while trying to add the new student, try again later.\n");
        }
    }

    public static void displayReports(List<Student> students) {
        System.out.println("LIST OF STUDENT REPORTS\n");
        System.out.printf(Student.REPORT_FORMAT,
                "Id", "FirstName", "LastName", "Birthdate", "Age", "Group", "Java", "CSharp", "Python", "PHP");
        System.out.printf(Student.REPORT_FORMAT,
                "**", "*********", "********", "*********", "***", "*****", "****", "******", "******", "***");
        for (Student student : students) {
            System.out.println(student.showStudentReport());
        }
        System.out.println();
    }
}
