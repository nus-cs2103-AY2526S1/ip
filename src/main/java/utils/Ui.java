package utils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A util that helps with getting user input and showing program output. 
 */
public class Ui {
    Scanner sc;
    ArrayList<String> outputs;

    public Ui() {
        sc = new Scanner(System.in);
        outputs = new ArrayList<>();
    }

    /**
     * Returns the string that would have been printed out in console. 
     */
    public String getOutput() {
        String response = String.join("\n", outputs);
        outputs.clear();

        return response;
    }

    /**
     * Records the outputs that would have been printed out. 
     * Was used by text based UI to echo the {@code message} to the chatbot user.
     * 
     * @param message message to be printed out by chatbot to the user.
     */
    public void echo(String message) {
        // System.out.println("\t" + message);
        outputs.add(message);
    }

    /**
     * Shows the horizontal line that wrap around the Chatbot output. 
     */
    public void showLine() {
        String horizontalLine = "____________________________________________________________";
        // System.out.println(horizontalLine);
    }

    /**
     * Shows welcome message to the users at the start. 
     */
    public void showWelcome() {
        final String BOT_NAME = "ApunableBot"; // A pure pineapple bot

        showLine();
        echo(String.format("Hello! I'm %s", BOT_NAME));
        echo("What can I do for you?");
        showLine();
    }

    /**
     * Shows error indicating the failure to load models from file.
     */
    public void showLoadingError() {
        echo("Failed to load models from file");
    }

    /**
     * Shows the error message if some exception is thrown. 
     * 
     * @param message the exception message. 
     */
    public void showError(String message) {
        echo(message);
    }

    /**
     * Reads user commands from {@code System.in}. 
     */
    public String readCommand() {
        String command = sc.nextLine();

        return command;
    }

    /**
     * [Unused method] Performs necessary actions(e.g. close the Scanner) before closing this ui and exit the program. 
     */
    public void close() {
        sc.close();
    }
}
