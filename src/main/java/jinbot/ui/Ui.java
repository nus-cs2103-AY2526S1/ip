package jinbot.ui;

import java.util.Scanner;

/**
 * Handles user interaction for JinBot, including reading input
 * and displaying messages with a consistent format.
 */
public class Ui {
    private static final String SPACING = "        ";
    private static final String INDENTATION = "  ";
    private static final String HORIZONTAL_LINE = SPACING
        + "______________________________________________________";
    private static final String GREET = "Hello! I'm JinBot\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    private final Scanner scanner;

    /**
     * Constructs Ui instance with a scanner
     * to read input from standard input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting message in a formatted box.
     */
    public void showGreeting() {
        printBox(GREET);
    }

    /**
     * Displays the goodbye message in a formatted box.
     */
    public void showGoodbye() {
        printBox(GOODBYE);
    }

    /**
     * Reads a line of user input from standard input.
     *
     * @return The next line of input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the given message inside a formatted box.
     *
     * @param message The message to display.
     */
    public void printBox(String message) {
        System.out.println(HORIZONTAL_LINE);
        for (String line : message.split("\\R")) {
            System.out.println(SPACING + INDENTATION + line);
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
