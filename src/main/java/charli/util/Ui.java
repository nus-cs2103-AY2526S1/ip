package charli.util;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the Charli chatbot.
 * Manages user input, output display, and formatted messages.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");

    }

    public void showError(String message) {
        System.out.println("    ERROR! " + message);
    }

    public void showLoadingError() {
        System.out.println("    Data file not found. Starting with an empty list.");
    }

}
