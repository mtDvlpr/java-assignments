package nl.inholland;

import java.util.Date;

public class Student extends Person {
    String group;

    public Student(String firstName, String lastName, Date birthdate, int age, String group) {
        super(firstName, lastName, birthdate, age);
        this.group = group;
        levelOfAccess = LevelOfAccess.Basic;
    }
}
