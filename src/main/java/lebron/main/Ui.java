package lebron.main;

import java.util.Scanner;

import lebron.common.Constants;
import lebron.exception.LeBronException;

/**
 * Handles user interactions, including displaying messages and reading input.
 */
public class Ui {
    private final Scanner sc;

    /**
     *  Constructor to initialize the Scanner for user input
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a loading error message when tasks cannot be loaded from the file.
     */
    public void showLoadingError() {
        System.out.println(Constants.UI_LOADING_ERROR);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(Constants.UI_WELCOME);
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user.
     * @throws LeBronException If the input is empty.
     */
    public String readCommand() throws LeBronException {
        String userInput = sc.nextLine();
        if (userInput.isEmpty()) {
            throw new LeBronException(Constants.UI_INPUT_EMPTY_ERROR);
        }
        return userInput;
    }

    /**
     * Displays the given message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String... message) {
        for (String msg : message) {
            assert !msg.trim().isEmpty() : "Message to display should not be empty";
            System.out.println(msg);
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
