package nailongbot.utils;

import java.util.Scanner;

/**
 * Handles all user interface interactions.
 * Responsible for displaying messages and reading user input.
 */
public class Ui {
    public static final String LINE = "________________________________\n";
    public static final String OPENING = LINE + "Hello! I'm NaiLongBot\nWhat can I do for you?\n" + LINE;
    public static final String CLOSING = LINE + "Bye. Hope to see you again soon!" + "\n" + LINE;

    private final Scanner scanner;

    /**
     * Constructor.
     * initializes a new Scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints out OPENING.
     */
    public void printWelcome() {
        System.out.println(OPENING);
    }

    public String getOpening() {
        return OPENING;
    }

    /**
     * Prints out LINE.
     */
    public void printLine() {
        System.out.print(LINE);
    }

    /**
     * Prints out users input.
     *
     * @param msg the message to be printed
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Prints out closing message.
     */
    public void printGoodbye() {
        printMessage(CLOSING);
    }
}
