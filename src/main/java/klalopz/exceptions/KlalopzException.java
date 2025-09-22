package klalopz.exceptions;

import klalopz.ui.TextUi;

/**
 * Represents exceptions specific to the Klalopz application.
 * This exception can be thrown when the program encounters errors
 * related to user input, task operations, or other domain-specific issues.
 * in a formatted style.
 */
public class KlalopzException {

    public String message;

    /**
     * Creates a new {@code KlalopzException} with the specified detail message.
     *
     * @param message The detail message to describe the exception.
     */
    public KlalopzException(String message) {
        this.message = message;
        TextUi textUi = new TextUi();

        textUi.showMessage(message);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
