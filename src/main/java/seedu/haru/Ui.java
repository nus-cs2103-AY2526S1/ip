package seedu.haru;

import java.util.Scanner;

/**
 * Handles all user interactions through the command line interface (CLI).
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message along with the application logo.
     *
     * @param logo the ASCII logo of the application
     */
    public void showWelcome(String logo) {
        System.out.println("    --------------------------------------");
        System.out.println("    Hello! I'm");
        System.out.println(logo);
        System.out.println("    What can I do for you today?");
        System.out.println("    --------------------------------------");
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public void showGoodbye() {
        System.out.println();
        System.out.println("    --------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    --------------------------------------");
    }

    /**
     * Displays an error message in a formatted box.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println();
        System.out.println("    --------------------------------------");
        System.out.println("    " + message);
        System.out.println("    --------------------------------------");
    }

    /**
     * Displays a general message in a formatted box.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println();
        System.out.println("    --------------------------------------");
        System.out.println(message);
        System.out.println("    --------------------------------------");
    }

    /**
     * Reads a user command from standard input.
     * Leading and trailing whitespace are trimmed.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        System.out.println();
        return sc.nextLine().trim();
    }

    public void close() {
        sc.close();
    }
}

