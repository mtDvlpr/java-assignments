package nl.inholland;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException { // Student.txt file is not found (wrong path)
        DataInitializer initializer = new DataInitializer();
        List<Person> persons = initializer.getPersons();
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}
