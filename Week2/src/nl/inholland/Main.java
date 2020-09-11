package nl.inholland;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        DataInitializer initializer = new DataInitializer();
        Scanner in = new Scanner(System.in);

        Person user = logIn(initializer.getPersons(), in);
        Menu menu = new Menu(user.levelOfAccess);

        Character choice;

        do {
            menu.showMenu();

            choice = enterChoice(user, in);
            menu.chooseMenuItem(choice, initializer, in);

        }
        while (!choice.equals('x'));
    }

    private static Person logIn(List<Person> persons, Scanner in) {

        do {
            System.out.print("Enter username: ");
            String username = in.nextLine();
            System.out.print("Enter password: ");
            String password = in.nextLine();

            for (Person person : persons) {
                if (person.username.equalsIgnoreCase(username) && person.checkPassword(password)) {
                    System.out.println();
                    return person;
                }
            }
            System.out.println("Invalid login credentials, try again.");
            System.out.println();
        }
        while (true);
    }

    private static Character enterChoice(Person user, Scanner in) {
        Character[] choices;

        switch (user.levelOfAccess) {
            case Admin:
                choices = new Character[]{'s', 't', 'a', 'r', 'g', 'x'};
                break;
            case Editor:
                choices = new Character[]{'s', 't', 'a', 'r', 'x'};
                break;
            default:
                choices = new Character[]{'s', 't', 'x'};
        }

        do {
            System.out.print("Please, enter a choice: ");
            Character choice = in.nextLine().toLowerCase().charAt(0);

            for (Character c : choices) {
                if (choice.equals(c)) {
                    System.out.println();
                    return choice;
                }
            }
            System.out.println("Invalid choice, please try again.");
            System.out.println();
        }
        while (true);
    }
}
