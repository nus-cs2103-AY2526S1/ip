package aurora.ui;

import java.util.Scanner;

/**
 * Handles user interaction for the chatbot.
 * <p>
 * This class is responsible for displaying messages to the user and reading input
 * from the user through a {@link Scanner}.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Creates a new {@code Ui} instance with the given {@link Scanner}.
     *
     * @param scanner the {@link Scanner} used to read user input
     */
    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prints a message to the console prefixed with "Aurora: ".
     *
     * @param text the message to display
     */
    public void speak(String text) {
        System.out.println("Aurora: " + text);
    }

    /**
     * Displays the greeting message.
     */
    public void speakIntro() {
        speak("Hello! I'm Aurora. What can I do for you?");
    }

    /**
     * Displays the goodbye message.
     */
    public void speakOutro() {
        speak("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a non-empty line of input from the user.
     *
     * @return the user input as a non-empty {@link String}
     */
    public String readInput() {
        String input = null;
        while (input == null || input.isEmpty()) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
        }
        return input;
    }
}
