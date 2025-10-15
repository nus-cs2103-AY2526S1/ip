package bro.ui;

import java.util.Scanner;

/**
 * Handles user interactions, including reading input and displaying messages.
 */
public class Ui {
    private static final String NAME = "Bro";
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Reads a line of input from the user.
     *
     * @return The input string, or "blank" if the input is empty.
     */
    public String readInput() {
        String input = scanner.nextLine();
        return (input.isBlank() ? "blank" : input);
    }

    /**
     * Prints the welcome message when the application starts.
     *
     * @return The welcome message string.
     */
    public static String printHello() {
        return String.format(
                "Hello bro! I'm %s\nWhat can a brother do for you?",
                NAME);
    }
}
