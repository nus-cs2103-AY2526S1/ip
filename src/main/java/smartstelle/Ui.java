package smartstelle;

import java.util.Scanner;

/**
 * Handles all interactions with the user in the SmartStelle application.
 * <p>
 * Responsible for reading user input and displaying messages, errors, and UI
 * lines.
 * Provides welcome and goodbye messages to the user.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructs a new Ui object with a Scanner to read user input from standard
     * input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a welcome message with the SmartStelle logo to the user.
     * Also prints a separating line for clarity.
     */
    public void showWelcome() {
        String logo = "  _____      _____\n" +
                " /          /     \n" +
                " \\_____    \\_____ \n" +
                "       \\         \\\n" +
                "  _____/   _____/\n";

        System.out.println(logo + "Hello! I am SmartStelle. What can I help you with?");
        showLine();
    }

    /**
     * Displays a goodbye message to the user.
     * Also prints a separating line and closes the input Scanner.
     */
    public void showGoodbye() {
        System.out.println("    Bye, talk to you next time!");
        showLine();
        sc.close();
    }

    /**
     * Displays a general message to the user.
     *
     * @param msg The message to display.
     */
//    public String showMessage(String msg) {
//        System.out.println(msg);
//    }

    /**
     * Displays an error message to the user with a decorative border for emphasis.
     *
     * @param msg The error message to display.
     */
    public void showError(String msg) {
        System.out.println("\n**********************************\n" + msg + "\n**********************************\n");
    }

    /**
     * Prints a separating line to the console for clarity in the UI output.
     */
    public void showLine() {
        System.out.println("\n____________________________________________________________\n");
    }
}
