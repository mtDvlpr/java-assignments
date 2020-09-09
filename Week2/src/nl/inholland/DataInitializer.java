package nl.inholland;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataInitializer {
    List<Person> persons;

    public DataInitializer() throws FileNotFoundException {
        readStudents();
        readTeachers();
        readManagers();
    }

    private void readStudents() throws FileNotFoundException {
        Scanner studentScanner = new Scanner(new File("src\\nl\\inholland\\Students.txt"));
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
            Manager manager = new Manager(Integer.parseInt(managerArray[0]), managerArray[1], managerArray[2], LocalDate.parse(managerArray[3]), managerArray[5], managerArray[6]);
            persons.add(manager);
        }
        managerScanner.close();
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

    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Teacher) {
                teachers.add((Teacher)person);
            }
        }
        return teachers;
    }

    public List<Manager> getManagers() {
        List<Manager> managers = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Manager) {
                managers.add((Manager)person);
            }
        }
        return managers;
    }

}
