package seedu.mani.ui;

import java.util.Scanner;

/**
 * Handles all user interface interactions, including input and output.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the UI component with a scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user input.
     *
     * @return
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message when the application starts
     */
    public void welcomeMessage() {
        System.out.println("Hello! I'm mani.ui.Mani\nWhat can I do for you?");
    }

    /**
     * Displays the goodbye message when the application ends
     */
    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays system message to user.
     *
     * @param message
     */
    public void systemMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays error message to user.
     *
     * @param message
     */
    public void errorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Closes the scanner to prevent leaks
     */
    public void closeScanner() {
        this.scanner.close();
    }
}
