package nl.inholland;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a scanner to read user input
        Scanner in = new Scanner(System.in);
        String input;

        // Ask for group size until a valid answer is given
        do {
            System.out.print("How many students are in the group? ");
            input = in.nextLine();
            if (invalidInt(input)) {
                System.out.println("That was not a valid number.");
            }
            else if (Integer.parseInt(input) < 1) {
                System.out.println("Please enter a number greater than 0.");
            }
        }
        while (invalidInt(input) || Integer.parseInt(input) < 1);

        // Print the input
        int size = Integer.parseInt(input);
        System.out.println("Group size: " + size);
        System.out.println();

        // Create Student array with given size
        Student[] students = new Student[size];

        // Fill array with student names
        for (int i = 0; i < students.length; i++) {
            System.out.printf("Please enter the name of student #%s and press [ENTER]: ", i + 1);
            String name = in.nextLine();
            students[i] = new Student(name);
        }

        System.out.println();

        // Print the student names
        for (int i = 0; i < students.length; i++) {
            System.out.printf("Student #%s: %s", i + 1, students[i].name);
            System.out.println();
        }

        System.out.println();

        // Fill array with present value
        for (int i = 0; i < students.length; i++) {
            boolean validAnswer;
            do {
                System.out.printf("Is student #%s (%s) present? [Y/N + ENTER]: ", i + 1, students[i].name);
                String answer = in.nextLine();
                validAnswer = (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N"));
                if (validAnswer) {
                    students[i].present = answer.equalsIgnoreCase("Y");
                } else {
                    System.out.println("That was an invalid answer.");
                }
            }
            while (!validAnswer);
        }
        
        System.out.println();

        // Show students and whether they're present
        for (int i = 0; i < students.length; i++) {
            System.out.printf("Student #%s: %-10s | Present: %s", i + 1, students[i].name, students[i].present);
            System.out.println();
        }
    }

    // Check if input is invalid integer
    static boolean invalidInt(String value) {
        try {
            Integer.parseInt(value);
            return false;
        }
        catch (NumberFormatException e) {
            return true;
        }
    }
}
