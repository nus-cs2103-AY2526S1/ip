package baymax.exception;

public class BaymaxException extends Exception {
    public BaymaxException(String msg) {
        super(msg);
    }

    public static class InvalidCommandException extends BaymaxException {
        public InvalidCommandException() {
            super("Unknown command detected. Don’t worry, I am still here to help.");
        }
    }

    public static class MissingDescriptionException extends BaymaxException {
        public MissingDescriptionException(String command) {
            super(String.format(
                    "Pardon me. The \"%s\" command needs more details. "
                            + "Please provide a description so I may assist you.", command));
        }
    }

    public static class MissingDeadlineException extends BaymaxException {
        public MissingDeadlineException() {
            super("I am sorry, I could not find a deadline. "
                    + "Please use: deadline <description> /by <deadline>.");
        }
    }

    public static class MissingEventDetailsException extends BaymaxException {
        public MissingEventDetailsException() {
            super("I am unable to schedule this event without full details. "
                    + "Please use: event <description> /from <start> /to <end>.");
        }
    }

    public static class InvalidIndexException extends BaymaxException {
        public InvalidIndexException(int index) {
            super(String.format(
                    "I have scanned your task list, but I cannot find a task numbered %d. "
                            + "Please check again.", index + 1));
        }

        public InvalidIndexException(String command) {
            super(String.format("I have scanned your task list, but that is an invalid argument. "
                            + "Please use: %s <index>.", command));
        }
    }

    public static class InvalidDateException extends BaymaxException {
        public InvalidDateException() {
            super("I detect a formatting error. For optimal results, input your deadline "
                    + "like this: yyyy-MM-dd (e.g 2025-08-05). I will wait right here.");
        }
    }
}
