package cattis.ui;

import java.util.Scanner;

import cattis.Constants;
import cattis.exception.CattisException;

/**
 * Handle all user input / output logics
 */
public class Ui {
    private final Scanner scanner;
    private String latestMessages;
    private boolean isError;

    /**
     * default constructor, reset {@code messages}
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.resetMessages();
        this.isError = false;
        assert "".equals(this.latestMessages);
    }

    public void showInitialMessages() {
        showMessage(Constants.GREETING_MSG);
    }

    /**
     * Reads the input from the console (by line)
     * @return the string input by user
     */
    public String readCommand() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("----");
    }

    /**
     * For {@code Main} class to send error and enable {@code isError} flag
     * @param err {@code CattisException} error
     */
    public void showError(CattisException err) {
        this.showMessage(err.toString());
        this.isError = true;
    }

    public String getLatestMessage() {
        return "".equals(this.latestMessages)
                ? Constants.EMPTY_MSG
                : this.latestMessages;
    }

    /**
     * Checks if there exists some deferred messages that are not properly handled.
     */
    public boolean hasMessages() {
        return "".equals(latestMessages);
    }

    /**
     * Getter for JavaFx component to obtain the status of the dialog
     */
    public boolean getUiStatus() {
        return this.isError;
    }

    /**
     * Method to call in each cycle after
     * the user sends a response and {@code Cattis} replies
     */
    public void resetMessages() {
        this.latestMessages = "";
        this.isError = false;
    }

    /**
     * Add message to the {@code latestMessages} while printing the result.
     * @param msg message to show
     */
    public void showMessage(String msg) {
        this.latestMessages += msg;
    }

    /**
     * displaying exit message
     */
    public void showExitMessages() {
        this.showMessage(Constants.EXIT_MSG);
    }
}
