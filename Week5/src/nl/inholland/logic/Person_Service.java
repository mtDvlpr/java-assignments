package nl.inholland.logic;

import nl.inholland.dal.Database;
import nl.inholland.model.Person;
import nl.inholland.model.Report;
import nl.inholland.model.Student;
import nl.inholland.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Person_Service {
    private final Database db = new Database();

    public Person validateUser(String username, String password) {
        for (Person person : db.getPersons()) {
            if (person.username.equals(username) && person.getPassword().equals(password)) {
                return person;
            }
        }
        return null;
    }

    public List<String> getGroups() { return db.getGroups(); }

    public List<Person> getPersons() {
        return db.getPersons();
    }

    public List<Student> getStudents() {
        return db.getStudents();
    }

    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        for (Person person : db.getPersons()) {
            if (person instanceof Teacher) {
                teachers.add((Teacher) person);
            }
        }
        return teachers;
    }

    public List<Report> getReports() {
        return db.getReports();
    }

    public void saveStudents(List<Student> students) {
        db.saveStudents(students);
    }

    public void saveTeachers(List<Teacher> teachers) {
        db.saveTeachers(teachers);
    }
}
