package nl.inholland;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class DataInitializer {
    private List<Person> persons;

    public DataInitializer() throws FileNotFoundException {
        initialize();
    }

    private void initialize() throws FileNotFoundException {
        persons = new ArrayList<>();
        readStudents();
        readTeachers();
        readManagers();
    }

    private void readStudents() throws FileNotFoundException {
        Scanner studentScanner = new Scanner(new File("Students.txt"));
        while (studentScanner.hasNextLine()) {
            String line = studentScanner.nextLine();
            String[] studentArray = line.split(", ");
            Student student = new Student(Integer.parseInt(studentArray[0]), studentArray[1], studentArray[2], LocalDate.parse(studentArray[3]), studentArray[4], studentArray[5], studentArray[6]);
            student.grades = Stream.of(Arrays.copyOfRange(studentArray, 7, studentArray.length)).mapToInt(Integer::parseInt).toArray();
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

    public List<Person> getPersons() throws FileNotFoundException {
        initialize();
        return persons;
    }

    public List<Student> getStudents() throws FileNotFoundException {
        initialize();
        List<Student> students = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Student) {
                students.add((Student)person);
            }
        }
        return students;
    }

    public List<Integer> getStudentIds() throws FileNotFoundException {
        List<Integer> ids = new ArrayList<>();
        for (Student student : getStudents()) {
            ids.add(student.id);
        }

        return ids;
    }

    public List<Teacher> getTeachers() throws FileNotFoundException {
        initialize();
        List<Teacher> teachers = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Teacher) {
                teachers.add((Teacher)person);
            }
        }
        return teachers;
    }

    public List<Manager> getManagers() throws FileNotFoundException {
        initialize();
        List<Manager> managers = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Manager) {
                managers.add((Manager)person);
            }
        }
        return managers;
    }

    public boolean addStudent(Student student) {
        try {
            Writer w = new FileWriter("Students.txt", true);
            String studentString = String.format("\n%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                    student.id,
                    student.firstName,
                    student.lastName,
                    student.birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    student.group,
                    student.username,
                    student.getPassword(),
                    0, 0, 0, 0);
            w.write(studentString);
            w.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
