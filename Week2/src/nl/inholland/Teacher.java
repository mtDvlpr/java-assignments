package nl.inholland;

import java.time.LocalDate;

public class Teacher extends Person {
    double salary;

    public Teacher(int id, String firstName, String lastName, LocalDate birthdate, double salary, String username, String password) {
        super(id, firstName, lastName, birthdate, username, password);
        this.salary = salary;
        levelOfAccess = LevelOfAccess.Editor;
    }
}
