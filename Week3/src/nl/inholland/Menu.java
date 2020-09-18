package nl.inholland;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private final LevelOfAccess ROLE;
    private final String BASIC_MENU;
    private final String EDITOR_MENU;
    private final String ADMIN_MENU;

    public Menu(LevelOfAccess role) {
        this.ROLE = role;
        BASIC_MENU = "S. Display Students | T. Display Teachers | X. Exit |\n";
        EDITOR_MENU = "S. Display Students | T. Display Teachers | A. Add Students | R. Display Reports | X. Exit |\n";
        ADMIN_MENU = "S. Display Students | T. Display Teachers | A. Add Students | R. Display Reports | X. Exit |\n";
    }

    public void showMenu() {
        switch (ROLE) {
            case Basic:
                System.out.println(BASIC_MENU);
                break;
            case Editor:
                System.out.println(EDITOR_MENU);
                break;
            case Admin:
                System.out.println(ADMIN_MENU);
                break;
        }
    }

    public Character[] getChoices() {
        switch (ROLE) {
            case Admin:
                return new Character[]{'s', 't', 'a', 'r', 'g', 'x'};
            case Editor:
                return new Character[]{'s', 't', 'a', 'r', 'x'};
            default:
                return new Character[]{'s', 't', 'x'};
        }
    }

    public void chooseMenuItem(Character choice, DataInitializer initializer, Scanner in) throws IOException {
        // Check which choice the user made and open the correct menu item
        switch (choice) {
            case 's':
                MenuItem.displayStudents(initializer.getStudents());
                break;
            case 't':
                MenuItem.displayTeachers(initializer.getTeachers());
                break;
            case 'a':
                MenuItem.addStudent(in, initializer);
                break;
            case 'r':
                MenuItem.displayReports(initializer, in);
                break;
            default:
                System.out.println("\nLeaving the program now ...");
                break;
        }
    }
}
