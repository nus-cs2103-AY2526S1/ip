package luna;

/**
 * Represents exceptions specific to the Luna application.
 */
public class LunaException extends Exception {

    /**
     * Constructs a LunaException with the specified message.
     *
     * @param message the detail message
     */
    public LunaException(String message) {
        super(message);
    }

    /**
     * Exception thrown when a command entered by the user is invalid.
     */
    public static class InvalidCommandException extends LunaException {
        /**
         * Constructs an InvalidCommandException with the specified message.
         *
         * @param message the detail message
         */
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when the user provides an invalid task number
     * for mark/unmark commands.
     */
    public static class InvalidTaskNumberException extends LunaException {
        /**
         * Constructs an InvalidTaskNumberException with the specified message.
         *
         * @param message the detail message
         */
        public InvalidTaskNumberException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when the user input is empty.
     */
    public static class EmptyInputException extends LunaException {
        /**
         * Constructs an EmptyInputException with the specified message.
         *
         * @param message the detail message
         */
        public EmptyInputException(String message) {
            super(message);
        }
    }
}
