package abang.ui;

import java.util.Scanner;

/**
 * Handles user interactions through the console.
 * Provides methods to display welcome and farewell messages,
 * read user input, and show error messages.
 */
public class UI {
    /** Scanner used for reading user input. */
    private Scanner sc;

    /**
     * Constructs a new UI object with a scanner for System.in.
     */
    public UI (){
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public static String showWelcome() {
        return "Hello! I'm Abang\nWhat can I do for you?";
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the user command as a String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Formats an error message for display.
     *
     * @param error the error message to display
     * @return the formatted error message prefixed with "Error: "
     */
    public String showError(String error) {
        return "Error: " + error;
    }
}
