package burh;

import java.util.Scanner;

/**
 * Handles all interactions with the user, including input and output.
 */
public class Ui {
    private final Scanner scanner;
    private static final String SEPARATOR = "_-".repeat(20);
    /**
     * Creates a Ui instance and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Return the welcome message to the user.
     *
     * @return Welcome string.
     */
    public String showWelcome() {
        return "Burh... what now?"
                + "\nUgh, fine. I'm Bruh. What do you want, burh? Make it quick.";
    }

    /**
     * Return the goodbye message to the user.
     *
     * @return Goodbye string.
     */
    public String showGoodbye() {
        String[] goodbyes = {
            "Burh, finally. Don't come back.",
            "Ugh, about time, burh. Go away.",
            "*sigh* Later, burh. Not that I care.",
            "Burh... whatever. I was getting tired of you anyway."
        };
        return goodbyes[(int)(Math.random() * goodbyes.length)];
    }

    /**
     * Displays a message indicating that the save data could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Save data not found");
    }

    /**
     * Displays a custom error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        String[] errorReactions = {
            "Burh... *sigh* " + message,
            "Burh, really? " + message,
            "Burh, I don't have time for this. " + message
        };
        System.out.println(errorReactions[(int)(Math.random() * errorReactions.length)]);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a decorative line to separate sections of output.
     */
    public void printLines() {
        System.out.println(SEPARATOR);
    }
}
