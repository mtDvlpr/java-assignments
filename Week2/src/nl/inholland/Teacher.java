package nl.inholland;

import java.util.Date;

public class Teacher extends Person {
    double salary;

    public Teacher(String firstName, String lastName, Date birthdate, int age, double salary) {
        super(firstName, lastName, birthdate, age);
        this.salary = salary;
        levelOfAccess = LevelOfAccess.Editor;
    }
}
