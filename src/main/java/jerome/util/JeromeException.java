package jerome.util;

/**
 * Custom exception class for Jerome-related errors.
 * Contains nested static subclasses for specific exception types.
 */
public class JeromeException extends Exception {

    /**
     * Constructs a general JeromeException with the specified message.
     *
     * @param message The error message.
     */
    public JeromeException(String message) {
        super(message);
    }

    /**
     * Exception thrown when the task type is not recognized (e.g. "blah").
     */
    public static class InvalidTaskTypeException extends JeromeException {
        public InvalidTaskTypeException() {
            super("Error! \n Oh deary me... I'm sorry, but I don't know what task this is :-(");
        }
    }

    /**
     * Exception thrown when a task is missing its description.
     */
    public static class EmptyTaskException extends JeromeException {
        public final String type;

        /**
         * Constructs an EmptyTaskException with the given task type.
         *
         * @param type The task type (e.g. "todo", "event").
         */
        public EmptyTaskException(String type) {
            super("Error! \n My bad g, the description of "
                    + type + " cannot be empty!");
            this.type = type;
        }
    }

    /**
     * Exception thrown when a task contains fields it should not have.
     * (e.g. todo with "/by", deadline with "/from")
     */
    public static class WrongfulArgumentException extends JeromeException {
        public final String type;
        public final String from;
        public final String to;
        public final String by;

        /**
         * Constructs a WrongfulArgumentException based on illegal flags.
         *
         * @param type The task type.
         * @param from Whether it wrongly includes "/from".
         * @param to   Whether it wrongly includes "/to".
         * @param by   Whether it wrongly includes "/by".
         */
        public WrongfulArgumentException(String type, String from, String to, String by) {
            super("Error!" + buildMessage(type, from, to, by));
            this.type = type;
            this.from = from;
            this.to = to;
            this.by = by;
        }

        private static String buildMessage(String type, String from, String to, String by) {
            StringBuilder sb = new StringBuilder("Aww, " + type + " does not take in the argument(s) ");
            boolean isExtra = false;


            if (by != null) {
                sb.append("'by'");
                isExtra = true;
            }
            if (from != null) {
                if (isExtra) {
                    sb.append(", ");
                }
                sb.append("'from'");
                isExtra = true;
            }
            if (to != null) {
                if (isExtra) {
                    sb.append(", ");
                }
                sb.append("'to'");
            }
            return sb.toString();
        }
    }

    /**
     * Exception thrown when required fields for a task are missing or invalid.
     */
    public static class InvalidTaskDeclarationException extends JeromeException {
        public final String type;

        /**
         * Constructs an InvalidTaskDeclarationException for the given task type.
         *
         * @param type The task type (e.g. "event", "deadline").
         */
        public InvalidTaskDeclarationException(String type) {
            super("Error! \n Im soooo Sorry... " + type + " is missing fields...");
            this.type = type;
        }
    }
}
