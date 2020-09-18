package nl.inholland;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Teacher extends Person {
    double salary;

    public Teacher(int id, String firstName, String lastName, LocalDate birthdate, double salary, String username, String password) {
        super(id, firstName, lastName, birthdate, username, password);
        this.salary = salary;
        levelOfAccess = LevelOfAccess.Editor;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-15s %-15s %-15s %-5s",
                id,
                firstName,
                lastName,
                birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                getAge());
    }
}
