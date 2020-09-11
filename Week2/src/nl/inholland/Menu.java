package nl.inholland;

import java.io.FileNotFoundException;
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

    public void chooseMenuItem(Character choice, DataInitializer initializer, Scanner in) throws FileNotFoundException {
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
}
