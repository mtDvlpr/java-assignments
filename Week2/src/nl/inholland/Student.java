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
