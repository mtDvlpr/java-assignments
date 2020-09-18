package nl.inholland;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        DataInitializer initializer = new DataInitializer();
        Scanner in = new Scanner(System.in);

        // Log in and initialize the menu with the access role of the user
        Person user = logIn(initializer.getPersons(), in);
        Menu menu = new Menu(user.levelOfAccess);

        Character choice;

        // Keep showing the menu until the user exits
        do {
            menu.showMenu();

            // Ask for a choice and let the menu open the correct menu item
            choice = ReportMenu.enterChoice(menu.getChoices(), in, "Please, enter a choice: ");
            menu.chooseMenuItem(choice, initializer, in);
        }
        while (!choice.equals('x'));
    }

    private static Person logIn(List<Person> persons, Scanner in) {

        do {
            // Ask the user to input username and password
            System.out.print("Enter username: ");
            String username = in.nextLine();
            System.out.print("Enter password: ");
            String password = in.nextLine();

            // Search the persons list for a matching person and return that person if found
            for (Person person : persons) {
                if (person.username.equalsIgnoreCase(username) && person.checkPassword(password)) {
                    System.out.println();
                    return person;
                }
            }
            // If not found show an error message and let the loop continue
            System.out.println("Invalid login credentials, try again.\n");
        }
        while (true);
    }
}
