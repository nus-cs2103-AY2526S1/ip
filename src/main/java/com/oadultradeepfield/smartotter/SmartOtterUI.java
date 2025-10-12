package com.oadultradeepfield.smartotter;

import java.util.Scanner;

import com.oadultradeepfield.smartotter.util.CustomIO;

/**
 * Handles the user interface for SmartOtter, including displaying messages and reading user input.
 */
public class SmartOtterUI {
    private final Scanner scanner;

    /**
     * Constructs a SmartOtterUI with the given scanner.
     */
    public SmartOtterUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting message to the user.
     */
    public void showGreeting() {
        showMessage(SmartOtterConstant.GREETING_MESSAGE_TEMPLATE);
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        CustomIO.printPretty(message);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the trimmed user input
     */
    public String readCommand() {
        return CustomIO.sanitizeInput(scanner.nextLine());
    }

    /**
     * Displays an error message to the user.
     *
     * @param error the error message
     */
    public void showError(String error) {
        CustomIO.printPretty(CustomIO.formatError(error));
    }
}
