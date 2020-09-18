package nl.inholland;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student extends Person {
    static final String STUDENT_FORMAT = "%-5s %-15s %-15s %-15s %-5s %-10s";
    static final String REPORT_FORMAT = STUDENT_FORMAT + " %-10s %-10s %-10s %-10s";
    String group;
    int[] grades;

    public Student(String firstName, String lastName, LocalDate birthdate, String group, String username, String password) {
        super(firstName, lastName, birthdate, username, password);
        this.group = group;
        grades = new int[4];
        levelOfAccess = LevelOfAccess.Basic;
    }

    public Student(int id, String firstName, String lastName, LocalDate birthdate, String group, String username, String password) {
        super(id, firstName, lastName, birthdate, username, password);
        this.group = group;
        grades = new int[4];
        levelOfAccess = LevelOfAccess.Basic;
    }

    private int retakes() {
        int retakes = 0;
        for (int grade : grades) {
            if (grade < 55) {
                retakes++;
            }
        }

        return retakes;
    }

    public void showReport() {
        System.out.println("Report of student " + firstName + " " + lastName + "\n");
        System.out.printf("Student Id: %-10s\n", id);
        System.out.printf("First Name: %-10s\n", firstName);
        System.out.printf("Last Name: %-10s\n", lastName);
        System.out.printf("Age: %-10s\n", getAge());

        System.out.printf("\n%-10s\n", "COURSES");
        System.out.printf("\nJava: %-10s\n", grades[0]);
        System.out.printf("CSharp: %-10s\n", grades[1]);
        System.out.printf("Python: %-10s\n", grades[2]);
        System.out.printf("PHP: %-10s\n", grades[3]);

        System.out.printf("\n%-10s\n", "RESULTS");
        System.out.printf("\nResult: %-10s\n", retakes() == 0 ? "Passed" : "Not Passed");
        System.out.printf("Retakes: %-10s\n", retakes());
    }

    public String showStudentReport() {
        return String.format(REPORT_FORMAT,
                id,
                firstName,
                lastName,
                birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                getAge(),
                group,
                grades[0], grades[1], grades[2], grades[3]);
    }

    @Override
    public String toString() {
        return String.format(STUDENT_FORMAT,
                id,
                firstName,
                lastName,
                birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                getAge(),
                group);
    }
}
