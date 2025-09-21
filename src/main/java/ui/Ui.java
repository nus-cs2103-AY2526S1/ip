package ui;

import java.util.Scanner;

/**
 * A utility class for reading user input and displaying program output.
 */
public class Ui {
    /**
     * A constant string for the horizontal line regularly used in the chatbot's output.
     */
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    private Scanner s;

    /**
     * Constructs a {@link Ui} object with a {@link Scanner} reading {@code System.in}.
     */
    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public void greet() {
        String greeting = "    Hello! I'm Clam!\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    /**
     * Prints the goodbye message.
     */
    public void bye() {
        chatbotPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the next line of user input with Scanner.
     *
     * @return the next line of user input as a String
     */
    public String getInput() {
        return s.nextLine();
    }

    /**
     * Prints the horizontal line with the chatbot indent.
     */
    public void printLine() {
        chatbotPrint(HORIZONTAL_LINE);
    }

    /**
     * Prints an (error) message with an "Error:" header and
     * the chatbot indent.
     * @param msg
     */
    public void showError(String msg) {
        chatbotPrint("Error: " + msg);
    }

    /**
     * Prints a string with the chatbot indent of 4 spaces.
     * @param s the String to be printed
     */
    public void chatbotPrint(String s) {
        System.out.println("    " + s);
    }

    /**
     * Closes the Scanner object.
     */
    public void close() {
        s.close();
    }
}
