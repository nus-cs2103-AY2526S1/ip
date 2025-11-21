package yap.io;

import java.util.Scanner;

/**
 * User interface class for handling input and output operations.
 */
public class Ui {
    private final Scanner in = new Scanner(System.in);

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Yap your new best friend!");
    }

    /**
     * Asks for and returns the user's name.
     *
     * @return the user's name
     */
    public String askName() {
        System.out.println("May I know what's your name?");
        String name = in.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Sorry, I didn't catch that. What's your name?");
            name = in.nextLine().trim();
        }
        System.out.printf("Nice to meet you, %s!%n", name);
        System.out.printf("How can i help you today?%n");
        return name;
    }

    /**
     * Reads a command from the user.
     *
     * @return the user's input command
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Shows a separator line.
     */
    public void showLine() {
        System.out.println("--------------------------------------------------");
    }

    /**
     * Shows a message to the user.
     *
     * @param msg the message to display
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Shows an error message to the user.
     *
     * @param error the error message to display
     */
    public void showError(String error) {
        System.out.println("☹ OOPS! " + error);
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        showError("I couldn't load your saved tasks. Starting fresh!");
    }

    /**
     * Shows a goodbye message to the user.
     *
     * @param name the user's name
     */
    public void showGoodbye(String name) {
        System.out.printf("Goodbye, %s!%n", name);
    }
}
