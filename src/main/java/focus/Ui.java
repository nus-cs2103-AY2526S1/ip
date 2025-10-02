package focus;

import java.util.Scanner;

/**
 * Handles user interactions, including reading commands and showing messages.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a Ui that reads from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the next line of user input.
     *
     * @return The next input line entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message Error message to display.
     */
    public void showError(String message) {
        System.out.println("     " + message);
    }

    /** Shows the welcome banner and greeting. */
    public void showWelcome() {
        printLine();
        System.out.println("    Hello! I'm Focus\n" + "    What can I do for you?");
        printLine();
    }

}
