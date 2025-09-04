package evansbot.ui;

import java.util.Scanner;

/**
 * Handles user interactions in the EvansBot application.
 * Responsible for reading user input, displaying messages, and showing errors.
 */
public class Ui {
    private final Scanner scanner;


    /**
     * Constructs a Ui instance and initializes the input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return "Hello! I'm EvansBot \n"
             + "What can I do for you?";
    }
    /**
     * Displays a farewell message to the user.
     */
    public String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Prints a line separator to visually separate sections of output.
     */
    public void showLine() {
        System.out.println("############################################################");
    }
}
