package burgerburglar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Handles all user interactions for BurgerBurglar.
 * <p>
 * Responsibilities include reading user input, printing messages, errors,
 * welcome/goodbye messages, task lists, and the user manual.
 */
public class Ui {
    private static final String LINE_BREAK =
            "______________________________________________________________________";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Shows the welcome message, including the BurgerBurglar logo and version,
     * followed by a user manual.
     *
     * @param logo    the ASCII art logo
     * @param version the version string
     */
    public void showWelcome(String logo, String version) {
        System.out.println(logo + "INITIATING BURGERBURGLAR " + version + "\n"
                + "------------------------------------------------------------------100%\n");
        showLine();
        printUserManual();
        showLine();
        System.out.println("GOOD DAY, GOOD BURGER.\nWHAT CAN BURGER STEAL FOR YOU?");
    }

    /**
     * Shows the goodbye message.
     *
     * @param version the version string
     */
    public void showGoodbye(String version) {
        showLine();
        System.out.println("GOODBYE, GOODBURGER.");
        showLine();
        System.out.println("EXITING BURGERBURGLAR " + version);
    }

    // Print a line break
    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    // Read input from the user
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        showLine();
        System.out.println("BURGER ERROR: " + message);
        showLine();
    }

    // General message printing
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Show the list of tasks (delegated to TaskList's toString)
    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    /** Shows the "BURGER" bonus message. */
    public void showBurger() {
        showLine();
        System.out.println("BURGER IS BURGER, AND YOU ARE THE FRIES.");
        showLine();
    }

    /** Prints the user manual loaded from a text file. */
    public void printUserManual() {
        try {
            String manual = Files.readString(Path.of("./data/user_manual.txt"));
            System.out.println(manual);
        } catch (IOException e) {
            System.out.println("BURGER ERROR: Could not load user manual.");
        }
    }
}
