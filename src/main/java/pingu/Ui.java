package pingu;

import java.util.Scanner;

/**
 * Handles all user interactions for the Pingu application.
 */
public class Ui {
    private static final String DIVIDER = "_".repeat(60);
    private static final String CHATBOT_NAME = "Pingu";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }
    
    /**
     * Reads the next full line of input from the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line divider.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the loading error message if the data file cannot be loaded.
     */
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with an empty task list.");
    }
    
    /**
     * Closes the scanner to release resources.
     */
    public void close() {
        scanner.close();
    }
}
