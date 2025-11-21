package david.ui;

import java.util.Scanner;

/**
 * Prints the output or error message.
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the command, and goes to the next line of the input.
     *
     * @return The command of the current line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String welcome = "Hello! I'm David.\n What can I do for you?";
        System.out.println(Formatter.format(welcome));
    }

    /**
     * Prints farewell message.
     */
    public void showBye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(Formatter.format(bye));
    }

    /**
     * Prints error if execution of one command fails.
     *
     * @param msg Error message of DavidException.
     */
    public void showError(String msg) {
        System.out.println(Formatter.format("Error: " + msg));
    }

    /**
     * Prints if loading the tasks fails.
     */
    public void showLoadingError() {
        System.out.println("Error: the tasks cannot be loaded.");
    }

    /**
     * Prints the formatted message.
     *
     * @param msg The messages to be formatted.
     */
    public void showMessage(String msg) {
        System.out.println(Formatter.format(msg));
    }

}
