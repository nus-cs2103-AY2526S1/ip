package mochibot;

/**
 * The {@code MochiBotException} class serves as the base exception type for
 * all custom exceptions used in the MochiBot application.
 * <p>
 * Specific error conditions, such as invalid commands or missing arguments,
 * are represented by static inner classes extending this exception.
 * </p>
 */
public class MochiBotException extends Exception {
    public MochiBotException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Exception thrown when there is an invalid or unrecognised
     * command inputted by the user.
     */
    public static class InvalidCommandException extends MochiBotException {
        private static final String errorMessage = """
                Invalid command! Please try again ~('O')~.
                """;

        public InvalidCommandException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user provides a task index that does not exist.
     */
    public static class InvalidTaskIndexException extends MochiBotException {
        private static final String errorMessage = """
                Sorry, but your task cannot be found (T^T).
                """;

        public InvalidTaskIndexException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user attempts to create a task without providing a name.
     */
    public static class MissingTaskNameException extends MochiBotException {
        private static final String errorMessage = """
                Your task name cannot be empty ( ◡̀_◡́).
                """;

        public MissingTaskNameException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user provides an empty or missing date-time value.
     */
    public static class MissingDateException extends MochiBotException {
        private static final String errorMessage = """
                Your date cannot be empty ( ◡̀_◡́).
                """;

        public MissingDateException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the deadline command is missing required
     * argument of {@code /by}.
     */
    public static class MissingDeadlineArgumentsException extends MochiBotException {
        private static final String errorMessage = """
                Incorrect format detected (ꐦㅍ_ㅍ). Format is:
                deadline [DESCRIPTION] /by [YYYY:MM:DD HH:mm]
                """;

        public MissingDeadlineArgumentsException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the deadline command is missing required
     * argument of {@code /from} or {@code /to}.
     */
    public static class MissingEventArgumentsException extends MochiBotException {
        private static final String errorMessage = """
                Incorrect format detected (ꐦㅍ_ㅍ). Format is:
                event [DESCRIPTION] /from [YYYY:MM:DD HH:mm] /to [YYYY:MM:DD HH:mm]
                """;

        public MissingEventArgumentsException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user provides no task index for commands
     * that require one, such as {@code mark}, {@code unmark}, or {@code delete}.
     */
    public static class MissingTaskIndexException extends MochiBotException {
        private static final String errorMessage = """
                Your task number cannot be empty ( ◡̀_◡́).
                """;

        public MissingTaskIndexException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user performs a search without providing a keyword.
     */
    public static class MissingFindKeywordException extends MochiBotException {
        private static final String errorMessage = """
                Your search keyword cannot be empty ( ◡̀_◡́).
                """;

        public MissingFindKeywordException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user attempts to add a task that already exists
     * in the task list.
     */
    public static class DuplicateTaskException extends MochiBotException {
        private static final String errorMessage = """
                You entered a duplicate task ^(-o-)^.
                """;

        public DuplicateTaskException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user inputs an invalid date or time.
     */
    public static class InvalidDateTimeException extends MochiBotException {
        private static final String errorMessage = """
                You entered an invalid date-time ( ◡̀_◡́).
                Correct format should be YYYY:MM:DD HH:mm.
                """;

        public InvalidDateTimeException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user attempts to mark an already marked task.
     */
    public static class DuplicateMarkTaskException extends MochiBotException {
        private static final String errorMessage = """
                Task has already been marked. ^(-o-)^.
                """;

        public DuplicateMarkTaskException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the user attempts to unmark an already unmarked task.
     */
    public static class DuplicateUnmarkTaskException extends MochiBotException {
        private static final String errorMessage = """
                Task has already been unmarked. ^(-o-)^.
                """;

        public DuplicateUnmarkTaskException() {
            super(errorMessage);
        }
    }

    /**
     * Exception thrown when the end date-time is before or the same as the
     * start date-time.
     */
    public static class InvalidDateTimeOrder extends MochiBotException {
        private static final String errorMessage = """
                Error! Your end date-time is before or the same as your start date-time ( ◡̀_◡́).
                """;

        public InvalidDateTimeOrder() {
            super(errorMessage);
        }
    }
}
