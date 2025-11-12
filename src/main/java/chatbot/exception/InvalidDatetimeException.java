package chatbot.exception;

/**
 * Exception thrown when a user enters an invalid date or time format.
 * This helps ensure that all date/time inputs conform to supported formats,
 * preventing parsing errors and maintaining consistent handling.
 */
public class InvalidDatetimeException extends RuntimeException {

    /**
     * Constructs a new {@code InvalidDateTimeException} with a detailed message
     * explaining the valid formats and providing examples.
     */
    public InvalidDatetimeException() {
        super("Invalid date/time. Use one of: yyyy-mm-dd, yyyy-mm-dd HHmm, dd-mm-yyyy, dd-mm-yyyy HHmm "
                + "(e.g., 2019-12-02 1800 or 02-12-2019 1800).");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

// Javadoc comments above were generated with assistance from ChatGPT.
