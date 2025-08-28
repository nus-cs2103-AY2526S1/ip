package ui;

import java.util.Scanner;

/**
 * Handles all user interactions for the Rainy application.
 */
public class Ui {
    private Scanner sc;
    private final String line = "____________________________________________________________\n";

    /**
     * Constructs a new {@code Ui} with a {@link Scanner} for reading user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return the full command entered by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println(line + "hi! i'm rainy! :D\nwhat can i do for u?\n" + line);
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public void showBye() {
        System.out.println(line + " bai bai! see u again >_<!\n" + line);
    }

    /**
     * Displays a horizontal line divider.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Displays an error message wrapped with divider lines.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(line + message + "\n" + line);
    }

    /**
     * Closes the {@link Scanner} used for reading input.
     */
    public void close() {
        sc.close();
    }
}
