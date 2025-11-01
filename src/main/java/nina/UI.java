package nina;

import static nina.Nina.LINE;

/**
 * Handles user interactions with the program
 */
public class UI {

    /**
     * Prints the greeting message when the program starts.
     */
    public String greet() {
        return LINE + "Hello, I am your personal assistant Nina\n"
                + "What can I do for you?\n" + LINE;
    }

    /**
     * Prints the exit message when the program is about to terminate.
     */
    public String exit() {
        return "See you again soon!\n";
    }

    /**
     * Prints an error message to inform the user of invalid input or issues.
     *
     * @param msg The error message to be displayed
     */
    public String showError(String msg) {
        return "OOPS!!! " + msg;
    }

}
