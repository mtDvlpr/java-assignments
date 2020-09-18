package nl.inholland;

import java.time.format.DateTimeFormatter;

public class Report {
    static final String REPORT_FORMAT = "%-5s %-15s %-15s %-15s %-5s %-10s %-10s %-10s %-10s %-10s";

    Student student;
    int java;
    int cSharp;
    int python;
    int php;

    public Report(Student student, int java, int cSharp, int python, int php) {
        this.student = student;
        this.java = java;
        this.cSharp = cSharp;
        this.python = python;
        this.php = php;
    }

    private int retakes() {
        int retakes = 0;
        for (int grade : new int[]{java, cSharp, python, php}) {
            if (grade < 55) {
                retakes++;
            }
        }

        return retakes;
    }

    public String showStudentWithGrades() {
        return String.format(REPORT_FORMAT,
                student.id,
                student.firstName,
                student.lastName,
                student.birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                student.getAge(),
                student.group,
                java, cSharp, python, php);
    }

    @Override
    public String toString() {
        return String.format("" +
                        "Report of student %s %s\n\n" +
                        "Student Id: %-10s\n" +
                        "First Name: %-10s\n" +
                        "Last Name: %-10s\n" +
                        "Age: %-10s\n\n" +
                        "%-10s\n\n" +
                        "Java: %-10s\n" +
                        "CSharp: %-10s\n" +
                        "Python: %-10s\n" +
                        "PHP: %-10s\n\n" +
                        "%-10s\n\n" +
                        "Result: %-10s\n" +
                        "Retakes: %-10s\n",
                student.firstName,
                student.lastName,
                student.id,
                student.firstName,
                student.lastName,
                student.getAge(),
                "COURSES",
                java,
                cSharp,
                python,
                php,
                "RESULTS",
                retakes() == 0 ? "Passed" : "Not Passed",
                retakes());
    }
}
