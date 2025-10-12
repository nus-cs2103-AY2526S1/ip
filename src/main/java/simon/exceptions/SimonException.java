package simon.exceptions;

/**
 * Custom exception class for the Simon chatbot application.
 * A <code>SimonException</code> represents errors specific to Simon's operation.
 */
public class SimonException extends Exception {
    /**
     * Constructs a SimonException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public SimonException(String message) {
        super(message);
    }

    /**
     * Exception thrown when a task-related command has missing or invalid arguments.
     */
    public static class EmptyTaskException extends SimonException {
        /**
         * Constructs an EmptyTaskException with the specified error message.
         *
         * @param message The error message describing the missing task arguments.
         */
        public EmptyTaskException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when the user enters an unrecognized command.
     */
    public static class UnknownCommandException extends SimonException {
        /**
         * Constructs an UnknownCommandException with the specified error message.
         *
         * @param message The error message describing the unknown command.
         */
        public UnknownCommandException(String message) {
            super(message);
        }
    }
}
