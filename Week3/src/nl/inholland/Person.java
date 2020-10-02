package nl.inholland;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {
    private static int nextId = 1;
    private final String PASSWORD;
    protected int id;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthdate;
    protected String username;
    protected LevelOfAccess levelOfAccess;

    public Person(String firstName, String lastName, LocalDate birthdate, String username, String password) {
        this.id = nextId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.username = username;
        this.PASSWORD = password;
        nextId++;
    }

    public Person(int id, String firstName, String lastName, LocalDate birthdate, String username, String password) {
        this(firstName, lastName, birthdate, username, password);
        this.id = id;
        nextId = id + 1;
    }

    public int getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    public boolean checkPassword(String password) {
        return this.PASSWORD.equals(password);
    }

    public String getPassword() {
        return PASSWORD;
    }

    @Override
    public String toString() {
        return "Person " + firstName + " " + lastName;
    }
}
