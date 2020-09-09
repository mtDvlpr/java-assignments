package nl.inholland;

import java.util.Date;

public class Manager extends Person {

    public Manager(String firstName, String lastName, Date birthdate, int age) {
        super(firstName, lastName, birthdate, age);
        levelOfAccess = LevelOfAccess.Admin;
    }
}
