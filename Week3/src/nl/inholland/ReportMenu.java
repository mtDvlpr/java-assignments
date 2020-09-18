package nl.inholland;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportMenu {
    private static final String MENU = "A. Add (Update) Report | S. Save Report | R. Display Reports | B. Back to Main | X. Exit |\n";
    private static final Character[] VALID_CHOICES = {'a', 'r', 's', 'b', 'x'};

    public static void showMenu() {
        System.out.println("\n" + MENU + "\n");
    }

    public static List<Integer> displayReports(DataInitializer initializer) {
        List<Integer> ids = new ArrayList<>();
        System.out.println("LIST OF STUDENT REPORTS\n");
        System.out.printf(Report.REPORT_FORMAT + "\n",
                "Id", "FirstName", "LastName", "Birthdate", "Age", "Group", "Java", "CSharp", "Python", "PHP");
        System.out.printf(Report.REPORT_FORMAT + "\n",
                "**", "*********", "********", "*********", "***", "*****", "****", "******", "******", "***");
        for (Student student : initializer.getStudents()) {
            System.out.println(initializer.getReportForStudent(student).showStudentWithGrades());
            ids.add(student.id);
        }
        System.out.println();
        return ids;
    }

    public static Character enterChoice(Scanner in, String message) {
        // Keep asking for a choice until a valid one is given
        do {
            System.out.print(message);
            String input = in.nextLine();

            // Check if only one character was given
            if (input.length() == 1) {
                Character choice = input.toLowerCase().charAt(0);

                // Check if input is a valid choice and if so return it
                for (Character c : VALID_CHOICES) {
                    if (choice.equals(c)) {
                        System.out.println();
                        return choice;
                    }
                }
            }

            // Show error message and let the loop continue
            System.out.println("Invalid choice, please try again.\n");
        }
        while (true);
    }

    public static void chooseMenuItem(Character choice, DataInitializer initializer, Scanner in, Report report) throws IOException {
        // Check which choice the user made and open the correct menu item
        switch (choice) {
            case 'a':
                addReport(in, initializer, report);
                break;
            case 's':
                saveReport(in, initializer, report);
                break;
            case 'r':
                System.out.println("display Report......................");
                break;
            case 'b':
                System.out.println();
                break;
            default:
                System.out.println("\nLeaving the program now ...");
                System.exit(0);
                break;
        }
    }

    private static void saveReport(Scanner in, DataInitializer initializer, Report report) throws IOException {
        initializer.saveReport(report);
        chooseMenuItem(enterChoice(in, "Select a menu: "), initializer, in, report);
    }

    private static void addReport(Scanner in, DataInitializer initializer, Report report) throws IOException {
        System.out.println("Adding report...");

        report.java = readInt(in, String.format("Current grade Java is: %s. Enter (new) grade: ", report.java));
        report.cSharp = readInt(in, String.format("Current grade CSharp is: %s. Enter (new) grade: ", report.cSharp));
        report.python = readInt(in, String.format("Current grade Python is: %s. Enter (new) grade: ", report.python));
        report.php = readInt(in, String.format("Current grade PHP is: %s. Enter (new) grade: ", report.php));

        if (initializer.updateReport(report)) {
            System.out.println("The report was successfully updated.");
        }
        else {
            System.out.println("Something went wrong while trying to update the report, try again later.");
        }

        chooseMenuItem(enterChoice(in, "Select a menu: "), initializer, in, report);
    }

    private static int readInt(Scanner in, String message) {
        String input;
        do {
            System.out.print(message);
            input = in.nextLine();
            if (tryParseInt(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("That was an invalid grade, please try again.");
        }
        while (true);
    }

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
