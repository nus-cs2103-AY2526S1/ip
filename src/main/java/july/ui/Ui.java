package july.ui;

import java.util.Scanner;

/**
 * Handles user interface interactions for the July task management application.
 * This class manages user input/output operations including reading user commands.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the complete line entered by the user as a string
     */
    public String read() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the greeting message when the application starts.
     */
    public void greet() {
        System.out.println("Hello I'm July, your helpful assistant");
    }

    /**
     * Displays the farewell message when the user exits the application.
     */
    public String bye() {
        return "Good bye, I'm always here for you.";
    }

    /**
     * Displays an apology message for unrecognized user input.
     */
    public String sorry() {
        return "Sorry I don't quite understand you :(";
    }
}
