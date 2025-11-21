package jamal.ui;

import java.util.Scanner;

/**
 * Initialise Scanner to detect inputs
 * Print statements for Welcome, Exit and Linebreaks
 *
 */
public class Ui {

    public static final String exitStatement = "Catch ya later!\n";
    protected static final String lineBreak = "____________________________________________________________\n";
    protected Scanner inputScanner;


    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Prints welcome statement
     */
    public void showWelcome() {
        System.out.println(
                lineBreak + "Yo! I'm Jamal\n" + "What can I do for ya?\n");
    }

    /**
     * Prints a single line
     */
    public void showLine() {
        System.out.println(lineBreak);
    }

    /**
     * Waits for input and scans the input as a string for parsing
     *
     * @return String of scanned input
     */
    public String readCommand() {
        return inputScanner.nextLine();
    }

    /**
     * Prints error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints message body
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Give string of list of commands and format
     *
     * @return String of commands and formatting
     */
    public static String showHelplist() {
        return "Here are the commands:\n"
                + "list\n"
                + "list ongoing\n"
                + "list overdue\n"
                + "list upcoming\n"
                + "find <keyword>\n"
                + "mark <tasknumber>\n"
                + "unmark <tasknumber>\n"
                + "prioritize <tasknumber> <priority>\n"
                + "delete <tasknumber>\n"
                + "todo <description>\n"
                + "deadline <description> /by <datetime>\n"
                + "    datetime format: YYYY-MM-DDTHH:MM:SS\n"
                + "event <description> /from <datetime> /to <datetime>\n"
                + "    datetime format: YYYY-MM-DDTHH:MM:SS\n"
                + "bye";
    }
}
