package chatbot.ui;

import java.util.Scanner;

/**
 * Handles user interactions with the chatbot.
 * The {@code Ui} class is responsible for reading user input
 * from the command line and processing it into commands.
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructs a new {@code Ui} object and initializes
     * the scanner to read input from the standard input stream.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of input entered by the user,
     * removes leading and trailing whitespace, and returns it.
     *
     * @return the trimmed user input as a {@code String}
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }
}

// Javadoc comments above were generated with assistance from ChatGPT.
