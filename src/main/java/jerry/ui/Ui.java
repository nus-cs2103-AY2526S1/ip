package jerry.ui;

import java.util.Scanner;

/**
 * The Ui class handles all user interaction which includes displaying messages to the user,
 * reading user input, and presenting task-related information in a
 * human-friendly format.
 */
public class Ui {

    private final Scanner sc;
    private String latest = "";

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Display a welcome message to the user as they first open the application.
     */
    public void showWelcome(String message) {
        System.out.println(message);
        this.latest = message;
    }

    public String getLastOutput() {
        return this.latest;
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("___________________________________________________");
    }

    /**
     * Displays an error message prefixed with "Oops!".
     * Also stores the message as the latest output.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        String errorMessage = "Oops! " + message;
        this.latest = errorMessage;
    }

    /**
     * Displays a default error message when resources fail to load.
     * Also stores the message as the latest output.
     */
    public void showLoadingError() {
        String text = "Failed to load resources. Please try again!";
        this.latest = text;
    }

    /**
     * Displays a given output message to the console.
     * Also stores the message as the latest output.
     *
     * @param text the message to display
     */
    public void displayOutput(String text) {
        System.out.println(text);
        this.latest = text;
    }

}
