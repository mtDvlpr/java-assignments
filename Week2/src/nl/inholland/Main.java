package nl.inholland;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Person user = logIn();

        switch (user.levelOfAccess) {
            case Basic:
                System.out.println("Logged in as basic");
                break;
            case Editor:
                System.out.println("Logged in as editor");
                break;
            case Admin:
                System.out.println("Logged in as admin");
                break;
        }
    }

    private static Person logIn() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        DataInitializer initializer = new DataInitializer();

        // Initialize data and store it in a list
        List<Person> persons = initializer.getPersons();

        do {
            System.out.print("Enter username: ");
            String username = in.nextLine();
            System.out.print("Enter password: ");
            String password = in.nextLine();
            in.close();

            for (Person person : persons) {
                if (person.username.equalsIgnoreCase(username) && person.checkPassword(password)) {
                    return person;
                }
            }
            System.out.println("Invalid login credentials, try again.");
            System.out.println();
        }
        while (true);
    }
}
