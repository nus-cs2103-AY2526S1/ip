package mon.ui;

import java.util.Scanner;

/**
 * Handles user interface interactions.
 */
public class Ui {
    private static final String INDENT = "    ";
    private static final String MESSAGE_WELCOME = "Hello I'm Mon. What can I do for you?";

    private final Scanner scanner;

    /**
     * Creates a new UI instance with a scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(INDENT + MESSAGE_WELCOME);
    }

    /**
     * Reads the next line of user input.
     * 
     * @return the user input string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a message to the user.
     * 
     * @param message the message to display
     */
    public void showMessage(String message) {
        if (!message.isEmpty()) {
            System.out.println(message);
        }
    }

    /**
     * Shows an error message to the user.
     * 
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(INDENT + message);
    }

    /**
     * Checks if there is more input available.
     * 
     * @return true if more input is available, false otherwise
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}
