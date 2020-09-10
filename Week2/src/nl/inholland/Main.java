package nl.inholland;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        DataInitializer initializer = new DataInitializer();
        Scanner in = new Scanner(System.in);

        Person user = logIn(initializer.getPersons(), in);
        Character choice;

        do {
            switch (user.levelOfAccess) {
                case Basic:
                    System.out.println("S. Display Students | T. Display Teachers | X. Exit |");
                    break;
                case Editor:
                case Admin:
                    System.out.println("S. Display Students | T. Display Teachers | A. Add Students | R. Display Reports | X. Exit |");
                    break;
            }
            System.out.println();

            choice = enterChoice(user, in);

            switch (choice) {
                case 's':
                    displayStudents(initializer.getStudents());
                    break;
                case 't':
                    displayTeachers(initializer.getTeachers());
                    break;
                case 'a':
                    addStudent(in, initializer);
                    break;
                case 'r': // This option will be added in week 3
                    System.out.println("This feature is not yet available.");
                    System.out.println();
                    break;
                default:
                    System.out.println();
                    System.out.println("Leaving the program now ...");
                    break;
            }
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

    private static void displayStudents(List<Student> students) {
        System.out.println("LIST OF STUDENTS");
        System.out.println();
        System.out.printf("%-5s %-15s %-15s %-15s %-5s %-5s",
                "Id", "FirstName", "LastName", "Birthdate", "Age", "Group");
        System.out.println();
        System.out.printf("%-5s %-15s %-15s %-15s %-5s %-5s",
                "**", "*********", "********", "*********", "***", "*****");
        System.out.println();
        for (Student student : students) {
            System.out.printf("%-5s %-15s %-15s %-15s %-5s %-5s",
                    student.id,
                    student.firstName,
                    student.lastName,
                    student.birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                    student.getAge(),
                    student.group);
            System.out.println();
        }
        System.out.println();
    }

    private static void displayTeachers(List<Teacher> teachers) {
        System.out.println("LIST OF TEACHERS");
        System.out.println();
        System.out.printf("%-5s %-15s %-15s %-15s %-5s", "Id", "FirstName", "LastName", "Birthdate", "Age");
        System.out.println();
        System.out.printf("%-5s %-15s %-15s %-15s %-5s", "**", "*********", "********", "*********", "***");
        System.out.println();
        for (Teacher teacher : teachers) {
            System.out.printf("%-5s %-15s %-15s %-15s %-5s",
                    teacher.id,
                    teacher.firstName,
                    teacher.lastName,
                    teacher.birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                    teacher.getAge());
            System.out.println();
        }
        System.out.println();
    }

    private static void addStudent(Scanner in, DataInitializer initializer) {
        System.out.println("ADD STUDENT");
        System.out.println();

        System.out.print("Choose a username: ");
        String username = in.nextLine();
        System.out.print("Choose a password: ");
        String password = in.nextLine();
        System.out.print("Enter a first name: ");
        String firstName = in.nextLine();
        System.out.print("Enter a last name: ");
        String lastName = in.nextLine();
        LocalDate birthdate = LocalDate.now();
        boolean invalidDate = true;
        do {
            try {
                System.out.print("Please enter date of birth in MM/DD/YYYY: ");
                birthdate = LocalDate.parse(in.nextLine(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                invalidDate =  false;
            }
            catch (Exception e) {
                System.out.println("That was not a valid date, try again.");
                System.out.println();
            }
        }
        while (invalidDate);

        System.out.print("Enter group: ");
        String group = in.nextLine();

        if (initializer.addStudent(new Student(firstName, lastName, birthdate, group, username, password))) {
            System.out.println("The data was successfully added!");
        }
        else {
            System.out.println("Something went wrong while trying to add the new student, try again later.");
        }
        System.out.println();
    }
}
