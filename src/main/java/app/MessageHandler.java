package app;

import errors.BoopError;

/**
 * The MessageHandler class is responsible for generating
 * user-facing messages for the Boop application.
 *
 * It provides greetings, task load confirmations,
 * and formatted error messages to maintain consistency
 * in the application's output.
 */
public final class MessageHandler {
    private final String name = "Boop";

    /**
     * Returns the greeting message shown when the application starts.
     *
     * @return a greeting string with the application's name
     */
    public String greeting() {
        return Messages.GREETING.formatted(name);
    }

    /**
     * Returns the confirmation message shown
     * after tasks are successfully loaded.
     *
     * @return a confirmation string
     */
    public String finishLoading() {
        return Messages.TASKS_LOADED;
    }

    /**
     * Returns a formatted error message to display when
     * an error occurs within the application.
     *
     * @param error the error that has occurred
     * @return a formatted string describing the error
     */
    public String errorMessage(BoopError error) {
        return Messages.ERROR_PREFIX.formatted(error.getMessage());
    }
}
