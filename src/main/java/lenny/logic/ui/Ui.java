package lenny.logic.ui;

import java.util.Scanner;

/**
 * Handles all interactions with the user,
 * including displaying messages and reading input.
 */

public class Ui {
    private final Scanner in = new Scanner(System.in);

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Lenny!");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads the next command from the user.
     *
     * @return The full user input as a string.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Reads the priority level of the task from the user.
     *
     * @return The priority level as an integer.
     */
    public int readPriority() {
        while (true) {
            System.out.print("Enter task priority (1–5): ");
            if (!in.hasNextLine()) {
                throw new IllegalStateException("No input available for priority.");
            }
            String s = in.nextLine().trim();
            try {
                int p = Integer.parseInt(s);
                if (p >= 1 && p <= 5) {
                    return p;
                }
            } catch (NumberFormatException ignored) { }

            System.out.println("Invalid priority. Please enter a number from 1 to 5.");
        }
    }

    /**
     * Displays an error message.
     *
     * @param message Error details to show.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a command response
     */
    public void showResponse(String response) {
        System.out.println(response);
    }

    /**
     * Displays a bye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
