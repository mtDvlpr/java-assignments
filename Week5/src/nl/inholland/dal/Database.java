package nl.inholland.dal;

import nl.inholland.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Database {
    private List<Person> persons;
    private List<Report> reports;

    public Database() {
        persons = new ArrayList<>();
        reports = new ArrayList<>();
        initialize();
    }

    public void initialize() {
        readStudents();
        readTeachers();
        readManagers();
    }

    private void readStudents() {
        try(Scanner studentScanner = new Scanner(new File("src/resources/students.csv"))) {
            while (true) {
                try {
                    String line = studentScanner.nextLine();
                    String[] studentArray = line.split(",");
                    Student student = new Student(Integer.parseInt(studentArray[0]), studentArray[1], studentArray[2], LocalDate.parse(studentArray[3]), studentArray[4], studentArray[5], studentArray[6]);
                    persons.add(student);
                }
                catch (NoSuchElementException nsee) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readTeachers() {
        try(Scanner teacherScanner = new Scanner(new File("src/resources/teachers.csv"))) {
            while (true) {
                try {
                    String line = teacherScanner.nextLine();
                    String[] teacherArray = line.split(",");
                    Teacher teacher = new Teacher(Integer.parseInt(teacherArray[0]), teacherArray[1], teacherArray[2], LocalDate.parse(teacherArray[3]), Double.parseDouble(teacherArray[4]), teacherArray[5], teacherArray[6]);
                    persons.add(teacher);
                }
                catch (NoSuchElementException nsee) {
                    break;
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private void readManagers() {
        try(Scanner ManagerScanner = new Scanner(new File("src/resources/managers.csv"))) {
            while (true) {
                try {
                    String line = ManagerScanner.nextLine();
                    String[] managerArray = line.split(",");
                    Manager manager = new Manager(Integer.parseInt(managerArray[0]), managerArray[1], managerArray[2], LocalDate.parse(managerArray[3]), managerArray[4], managerArray[5]);
                    persons.add(manager);
                }
                catch (NoSuchElementException nsee) {
                    break;
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private void readReports() {
        try(Scanner reportScanner = new Scanner(new File("src/resources/Reports.csv"))) {
            while (true) {
                try {
                    String line = reportScanner.nextLine();
                    String[] reportArray = line.split(",");
                    Report report = new Report(getStudentById(Integer.parseInt(reportArray[0])), Integer.parseInt(reportArray[1]), Integer.parseInt(reportArray[2]), Integer.parseInt(reportArray[3]), Integer.parseInt(reportArray[4]));
                    reports.add(report);
                }
                catch (NoSuchElementException nsee) {
                    break;
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public List<Report> getReports() { return reports; }

    public List<Person> getPersons() { return persons; }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Student) {
                students.add((Student)person);
            }
        }
        return students;
    }

    public Student getStudentById(int id) {
        for (Student student : getStudents()) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }

    public boolean saveStudents(List<Student> students) {
        try (Writer writer = new FileWriter("src/resources/students.csv")) {
            for (Student student : students) {
                String studentString = String.format("%s,%s,%s,%s,%s,%s,%s\n",
                        student.id,
                        student.firstName,
                        student.lastName,
                        student.birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        student.group,
                        student.username,
                        student.getPassword());
                writer.write(studentString);
            }

            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    public boolean saveTeachers(List<Teacher> teachers) {
        try (Writer writer = new FileWriter("src/resources/students.csv")) {
            for (Teacher teacher : teachers) {
                String teacherString = String.format("%s,%s,%s,%s,%s,%s,%s\n",
                        teacher.id,
                        teacher.firstName,
                        teacher.lastName,
                        teacher.birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        teacher.salary,
                        teacher.username,
                        teacher.getPassword());
                writer.write(teacherString);
            }

            return true;
        } catch (IOException ioe) {
            return false;
        }
    }
}
