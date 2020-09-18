package nl.inholland;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataInitializer {
    private List<Person> persons;
    private List<Report> reports;

    public DataInitializer() throws FileNotFoundException {
        initialize();
    }

    public void initialize() throws FileNotFoundException {
        persons = new ArrayList<>();
        readStudents();
        readTeachers();
        readManagers();
        readReports();
    }

    private void readStudents() throws FileNotFoundException {
        Scanner studentScanner = new Scanner(new File("Students.txt"));
        while (studentScanner.hasNextLine()) {
            String line = studentScanner.nextLine();
            String[] studentArray = line.split(", ");
            Student student = new Student(Integer.parseInt(studentArray[0]), studentArray[1], studentArray[2], LocalDate.parse(studentArray[3]), studentArray[4], studentArray[5], studentArray[6]);
            persons.add(student);
        }
        studentScanner.close();
    }

    private void readTeachers() throws FileNotFoundException {
        Scanner teacherScanner = new Scanner(new File("Teachers.txt"));
        while (teacherScanner.hasNextLine()) {
            String line = teacherScanner.nextLine();
            String[] teacherArray = line.split(", ");
            Teacher teacher = new Teacher(Integer.parseInt(teacherArray[0]), teacherArray[1], teacherArray[2], LocalDate.parse(teacherArray[3]), Double.parseDouble(teacherArray[4]), teacherArray[5], teacherArray[6]);
            persons.add(teacher);
        }
        teacherScanner.close();
    }

    private void readManagers() throws FileNotFoundException {
        Scanner managerScanner = new Scanner(new File("Managers.txt"));
        while (managerScanner.hasNextLine()) {
            String line = managerScanner.nextLine();
            String[] managerArray = line.split(", ");
            Manager manager = new Manager(Integer.parseInt(managerArray[0]), managerArray[1], managerArray[2], LocalDate.parse(managerArray[3]), managerArray[4], managerArray[5]);
            persons.add(manager);
        }
        managerScanner.close();
    }

    private void readReports() throws FileNotFoundException {
        reports = new ArrayList<>();
        Scanner reportScanner = new Scanner(new File("Reports.txt"));
        while (reportScanner.hasNextLine()) {
            String line = reportScanner.nextLine();
            String[] reportArray = line.split(", ");
            Report report = new Report(getStudentById(Integer.parseInt(reportArray[0])), Integer.parseInt(reportArray[1]), Integer.parseInt(reportArray[2]), Integer.parseInt(reportArray[3]), Integer.parseInt(reportArray[4]));
            reports.add(report);
        }
        reportScanner.close();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Student) {
                students.add((Student)person);
            }
        }
        return students;
    }

    private Student getStudentById(int id) {
        for (Student student : getStudents()) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }

    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Teacher) {
                teachers.add((Teacher)person);
            }
        }
        return teachers;
    }

    public Report getReportForStudent(Student student) {
        for (Report report : reports) {
            if (report.student.equals(student)) {
                return report;
            }
        }
        return null;
    }

    public boolean addStudent(Student student) {
        try {
            // Save new student
            Writer w1 = new FileWriter("Students.txt", true);
            String studentString = String.format("\n%s, %s, %s, %s, %s, %s, %s",
                    student.id,
                    student.firstName,
                    student.lastName,
                    student.birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    student.group,
                    student.username,
                    student.getPassword());
            w1.write(studentString);
            w1.close();

            // Save new student report
            Writer w2 = new FileWriter("Reports.txt", true);
            String reportString = String.format("\n%s, %s, %s, %s, %s", student.id, 0, 0, 0, 0);
            w2.write(reportString);
            w2.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateReport(Report report) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of("Reports.txt"), StandardCharsets.UTF_8));
            String reportString = String.format("%s, %s, %s, %s, %s",
                    report.student.id,
                    report.java,
                    report.cSharp,
                    report.python,
                    report.php);

            for (int i = 0; i < fileContent.size(); i++) {
                Character studentId = fileContent.get(i).charAt(0);
                if (studentId.equals(Character.forDigit(report.student.id, 10))) {
                    fileContent.set(i, reportString);
                    break;
                }
            }

            Files.write(Path.of("Reports.txt"), fileContent, StandardCharsets.UTF_8);
            return true;
        }
        catch (Exception e) {
            return false;
        }


    }
}
