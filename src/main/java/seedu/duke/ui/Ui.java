package seedu.duke.ui;

import java.util.Scanner;

/**
 * Handles user interaction for both CLI and GUI.
 * <p>For GUI, messages are buffered and retrievable via {@link #flush()}.</p>
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /** Buffer to collect messages for GUI. */
    private final StringBuilder buffer = new StringBuilder();

    private void addToBuffer(String msg) {
        buffer.append(msg).append(System.lineSeparator());
    }

    /**
     * Returns all accumulated output and clears the buffer.
     *
     * @return buffered output
     */
    public String flush() {
        String out = buffer.toString();
        buffer.setLength(0); // clear
        return out;
    }

    /** Shows the welcome banner (also buffers it). */
    public void showWelcome() {
        String msg = " Hello! I'm Chatnius\n";
        System.out.println(msg);
        addToBuffer(msg);
    }

    /** Shows the goodbye banner (also buffers it). */
    public void showGoodbye() {
        String msg = " Byebye! Use me again PLEASE\n";
        System.out.println(msg);
        addToBuffer(msg);
    }

    /**
     * Reads the next command line from standard input.
     *
     * @return the raw command line
     */
    public String readCommand() {
        System.out.print("What you need me for? ");
        return scanner.nextLine();
    }

    /**
     * Shows a normal message (also buffers it).
     *
     * @param msg message to print
     */
    public void show(String msg) {
        System.out.println(msg);
        addToBuffer(msg);
    }

    /**
     * Shows an error message (also buffers it).
     *
     * @param msg error text
     */
    public void showError(String msg) {
        System.out.println(msg);
        addToBuffer(msg);
    }

    /** Closes any resources (e.g., the scanner). */
    public void close() {
        scanner.close();
    }
}
