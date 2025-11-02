package olaf;

import java.util.Scanner;

/**
 * Handles all user interactions, includes input and output.
 * Provides methods to show welcome and goodbye messages.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays the welcome message when application starts.
     */
    public void showWelcome() {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Heyyos! I'm Olaf! Your personal assistant:)");
        System.out.println("  What can I do for you in this beautiful day?");
        System.out.println("  -----------------------------------------------------------------");
    }

    /**
     * Displays the goodbye message when the application ends
     */
    public void showBye() {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Goodbye! Hope to see you again soon!");
        System.out.println("  -----------------------------------------------------------------");
    }

    /**
     * Reads a full line of user input from the standard input.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows an error message to the user.
     *
     * @param msg Error message string to show to user
     */
    public void showError(String msg) {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  " + msg);
        System.out.println("  -----------------------------------------------------------------");
    }

    /**
     * Shows a message to the user.
     *
     * @param msg Message string to be shown to the user
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }
}

