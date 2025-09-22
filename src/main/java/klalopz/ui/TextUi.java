package klalopz.ui;

import klalopz.exceptions.KlalopzException;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles user interaction by displaying messages, lines, and errors in the console.
 * Provides methods for showing opening and closing messages, generic messages,
 * and throwing formatted exceptions.
 */

public class TextUi {
    public static final String botName = "klalopz";
    public static final String lineGap = "________________________________________________________";
    public static final String introMessage = "Hello! I'm " + botName + "!\n" +
            "What can I do for you today?\n" +
            "Type 'help' to see all available commands.";
    public static final String closingMessage = "Bye-bye, hope to see you soon!";
    public static final String errorMessage = "The following error has occurred: ";
    private final List<String> messages = new ArrayList<>();

    public List<String> getMessages() {
        return messages;
    }

    public void clearMessages() {
        messages.clear();
    }
    /**
     * Displays the opening message when the application starts,
     * surrounded by divider lines.
     */
    public void sayOpening() {
        showLine();
        messages.add(introMessage);
        showLine();
    }

    /**
     * Prints a horizontal line to the console for visual separation.
     */
    public void showLine() {
        messages.add(lineGap);
    }

    /**
     * Displays the closing message when the application exits,
     * followed by a divider line.
     */
    public void sayClosing() {
        messages.add(closingMessage);
        messages.add(lineGap);
    }

    /**
     * Displays a message to the user.
     *
     * @param input The message string to display.
     */
    public void showMessage(String input) {
        messages.add(input);
    }



}
