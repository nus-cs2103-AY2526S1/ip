package alpaca;

import java.util.Scanner;

/**
 * Handles interactions with the user via console.
 * Stores the last shown message for potential retrieval.
 */
public class Ui {

    /** Scanner for reading user input. */
    private final Scanner sc;

    /** Stores the last message shown to the user. */
    private String lastMessage;

    /**
     * Creates a new Ui instance with a Scanner for standard input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Shows a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Alpaca 🦙");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows an error message indicating tasks could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The trimmed user input.
     */
    public String readCommand() {
        String input = sc.nextLine();
        assert input != null : "Input should not be null";
        return input.trim();
    }

    /**
     * Shows a goodbye message to the user.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon! 🌈");
        showLine();
    }

    /**
     * Shows a message to the user and stores it internally.
     *
     * @param msg Message to display.
     */
    public void showMessage(String msg) {
        assert msg != null : "Message cannot be null";
        lastMessage = msg;
        showLine();
        System.out.println(msg);
        showLine();
    }

    /**
     * Returns the last message shown to the user.
     *
     * @return Last displayed message.
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Closes the Scanner. Should be called when the application exits.
     */
    public void close() {
        sc.close();
    }
}
