package nl.inholland;

import java.time.LocalDate;

public class Student extends Person {
    String group;

    public Student(int id, String firstName, String lastName, LocalDate birthdate, String group, String username, String password) {
        super(id, firstName, lastName, birthdate, username, password);
        this.group = group;
        levelOfAccess = LevelOfAccess.Basic;
    }

    @Override
    public String toString() {
        return "Student " + firstName + " " + lastName;
    }
}
