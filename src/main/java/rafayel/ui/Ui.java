package rafayel.ui;

import rafayel.RafayelException;

/**
 * Ui class handles all user interface interactions for the Rafayel chatbot.
 * Provides methods to display messages, errors, and formatted output to the user.
 */
public class Ui {
    /* Default messages to send */
    String LINE = "____________________________________________________________";
    String START_MSG = LINE + "\n" + " Hello! I'm Rafayel ^-^\n" + " What can I do for you?\n" + LINE;
    String END_MSG = " Bye. Hope to see you again soon!\n" + LINE;

    /**
     * Displays a horizontal line to separate different sections of the output.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        System.out.println(START_MSG);
    }

    /**
     * Displays the exit message when the chatbot is closing.
     */
    public void showExit() {
        System.out.println(END_MSG);
    }

    /**
     * Displays a loading error message when there is an issue loading tasks from storage.
     */
    public void showLoadingError() {
        // Rafayel Error
        System.out.println("Loading error!");

    }

    // printNewTaskString

    /**
     * Displays an error message to the user and throws a RafayelException.
     *
     * @param e the error message to display.
     * @throws RafayelException with the provided error message.
     */
    public void showError(String e) throws RafayelException {
        // System.out.println("Error!");
        throw new RafayelException("Error!\n" + e);
    }

}
