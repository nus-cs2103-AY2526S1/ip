package mel.exceptions;

/**
 * Contains all the different types of exceptions for the Mel chatbot
 */
public class MelException extends Exception {

    /**
     * Constructor for MelException
     *
     * @param msg
     */
    public MelException(String msg) {
        super(msg);

    }

    /**
     * Represents the exception when no argument is found for the command
     */
    public static class NoArgumentFoundException extends MelException {
        public NoArgumentFoundException(String command) {
            super(editMessage(command));

        }
        
        /**
         * Changes the exception message according to which command was incorrectly used
         */
        private static String editMessage(String command) {
            String message = "You are missing an argument!";
            if (command.equals("index")) {
                message = "You need a valid index after the command.";

            } else if (command.equals("todo")) {
                message = "You need a task description after \"todo\".";

            } else if (command.equals("deadline")) {
                message = "You are missing the deadline and/or /by after \"deadline\".";
            } else if (command.equals("event")) {
                message = "Use the event command like this: \"(event) /from (start) /to (end)\".";
            }
            return message;

        }
    }

    /**
     * Represents the exception when an invalid index is used
     */
    public static class InvalidIndexException extends MelException {
        public InvalidIndexException(String msg) {
            super(msg);

        }
    }

    /**
     * Represents the exception when an extra argument is used
     */
    public static class ExtraArgumentException extends MelException {
        public ExtraArgumentException(String command) {
            super("Are you trying to type " + command
                    + "? Just type " + "\"" + command + "\"" + "!");

        }
    }

    /**
     * Represents the exception when using commands when the list is empty
     */
    public static class EmptyListException extends MelException {
        public EmptyListException() {
            super("List is empty!");

        }
    }

}
