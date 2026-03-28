package cattis.exception;

/**
 * Represents the root exception type for all errors that occur within the <code>Cattis</code> application.
 * <p>
 * This class centralizes common error messages and provides a consistent way to handle
 * exceptions thrown during command execution, file operations, and input parsing.
 */
public class CattisException extends Exception {

    // Error messages

    /** Error message for accessing a task index that is out of bounds. */
    public static final String TASK_OUT_OF_BOUND = "Tasks out of bounded";

    /** Error message for missing required input fields. */
    public static final String EMPTY_FIELD = "Fields must not be left empty.";

    /** Format hint for a valid 'todo' command. */
    public static final String FORMAT_TODO = "todo [task]";

    /** Format hint for a valid 'deadline' command. */
    public static final String FORMAT_DEADLINE = "deadline [task] /by [Deadline]";

    /** Format hint for a valid 'event' command. */
    public static final String FORMAT_EVENT = "event [task] /from [Start] /to [End]";

    /** Error message for file loading failure. */
    public static final String LOAD_FILE = "⚠ Failed to load file. %s";

    /** Error message for file saving failure. */
    public static final String SAVE_FILE = "⚠ Failed to save file. %s";

    /** Error message for general parsing errors. */
    public static final String PARSING = "⚠ OOPS! Parsing error for %s";

    /** Error message for time parsing failures. */
    public static final String PARSE_TIME = "Failed to parse time for format ";

    /** Error message for incorrect 'todo' command format. */
    public static final String INCORRECT_FORMAT_TODO = "Invalid format.\nCorrect format: " + FORMAT_TODO;

    /** Error message for incorrect 'deadline' command format. */
    public static final String INCORRECT_FORMAT_DEADLINE = "Invalid format.\nCorrect format: " + FORMAT_DEADLINE;

    /** Error message for incorrect 'event' command format. */
    public static final String INCORRECT_FORMAT_EVENT = "Invalid format.\nCorrect format: " + FORMAT_EVENT;

    /**
     * Constructs a new {@code CattisException} with the specified error message.
     *
     * @param msg the detail message describing the exception
     */
    public CattisException(String msg) {
        super(msg);
    }

    /**
     * Returns a formatted string representation of this exception,
     * prefixed with a warning symbol and message context.
     *
     * @return a user-friendly error message
     */
    @Override
    public String toString() {
        return String.format("⚠ OOPS! %s", super.getMessage());
    }
}
