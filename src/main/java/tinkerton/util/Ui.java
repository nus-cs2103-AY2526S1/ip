package tinkerton.util;

import java.util.Scanner;

/**
 * Handles user interaction, input, and output for the Tinkerton application.
 */
public class Ui {
    /** Scanner for reading user input. */
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the introduction message to the user.
     */
    public void intro() {
        System.out.println("Hello! I'm Tinkerton");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the specified string to the console.
     *
     * @param string The string to print.
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Prints an error message to the console.
     *
     * @param string The error message to print.
     */
    public void showError(String string) {
        System.out.println(string);
    }

    /**
     * Closes the input scanner.
     */
    public void close() {
        scanner.close();
    }
}
