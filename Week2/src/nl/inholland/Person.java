package nl.inholland;

import java.util.Date;

public class Person {
    static int nextId = 1;
    int id;
    String firstName;
    String lastName;
    Date birthdate;
    int age;
    String username;
    String password;
    LevelOfAccess levelOfAccess;

    public Person(String firstName, String lastName, Date birthdate, int age, String username, String password) {
        this.id = nextId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.age = age;
        this.username = username;
        this.password = password;

        nextId++;
    }
}
