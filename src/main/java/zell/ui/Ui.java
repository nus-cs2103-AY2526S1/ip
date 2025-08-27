package zell.ui;

import java.util.Scanner;

/**
 * Deals with all things Ui related for the Zell chatbot.
 * Like getting the user's input and displaying messages to the user
 */
public class Ui {
    /** The Scanner object used to get user's input */
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the user's input.
     *
     * @return The user's input
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Displays messages to the user.
     *
     * @param message The message to be displayed to the user. Can also be an error message.
     */
    public void showMessage(String message) {
        System.out.println(formatMessage(message));
    }

    /**
     * Formats messages in a more aesthetic
     * manner.
     *
     * @param message The message to be displayed to the user. Can also be an error message.
     * @return The properly formatted message.
     */
    private static String formatMessage(String message) {
        return "________________________________________" +
                "__________________________________________________\n" +
                message +
                "\n__________________________________" +
                "________________________________________________________\n\n";
    }
}
