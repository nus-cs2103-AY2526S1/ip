
package rafayel.ui;

import rafayel.RafayelException;

/**
 * Ui class handles all user interface interactions for the Rafayel chatbot.
 * Provides methods to display messages, errors, and formatted output to the user.
 */
public class Ui {
    /** Default messages to send */
    private final String START_MSG = "Hello! I'm Rafayel ^-^\n" + " What can I do for you today?\n";
    private final String END_MSG = "Bye, come back soon, don't make me wait too long .-.\n";
    private final String LOADING_ERROR = "Loading error!";

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public String showWelcome() {
        return START_MSG;
    }

    /**
     * Displays the exit message when the chatbot is closing.
     */
    public String showExit() {
        return END_MSG;
    }

    /**
     * Displays a loading error message when there is an issue loading tasks from storage.
     */
    public String showLoadingError() {
        return LOADING_ERROR;

    }

    /**
     * Displays an error message to the user and throws a RafayelException.
     *
     * @param e the error message to display.
     * @throws RafayelException with the provided error message.
     */
    public void showError(String e) throws RafayelException {
        throw new RafayelException("Error!\n" + e);
    }

}
