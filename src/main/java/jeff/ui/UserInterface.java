package jeff.ui;

import java.util.Scanner;

/**
 * Handles user interface operations for the Jeff chatbot. Manages user
 * input/output.
 */
public class UserInterface {

    private final Scanner sc;

    /**
     * Constructor for the UserInterface class with a Scanner for reading user
     * input.
     */
    public UserInterface() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user when app starts.
     */
    public void welcome() {
        System.out.println("Hello! I am Jeff! Your own personal chatbot.");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads a command from the user via standard input. If input is empty,
     * returns empty string.
     *
     * @return the user's input command as a string
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return "";
    }

    /**
     * Prints separator line.
     */
    public void printLine() {
        System.out.println("______________________________");
    }

    /**
     * Prints error message.
     *
     * @param msg the error message to display
     */
    public void printError(String msg) {
        System.out.println("Error: " + msg);
    }

}
