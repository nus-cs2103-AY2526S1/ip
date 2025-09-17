package app;

/**
 * Central repository for all user-facing message strings.
 * Tailored to Duke with a "Great Sage" personality: authoritative, precise, and
 * confident.
 */
public final class Messages {

    public static final String GREETING = "Affirmative. I am %s, your Great Sage. How may I assist you today?";

    public static final String TASKS_LOADED = "Confirmed. All tasks have been loaded successfully.";

    public static final String ERROR_PREFIX = "Error | Execution failed: %s";

    public static final String COMMAND_FAREWELL = """
            Operation concluded. Farewell, and may your endeavors proceed without error.
            """;

    public static final String COMMAND_DEADLINE = """
            Task acknowledged:
            \t%s
            Current task count: %d
            """;

    public static final String COMMAND_DELETE = """
            Task deletion executed:
            \t%s
            Remaining tasks: %d
            """;

    public static final String COMMAND_EVENT = """
            Event registered:
            \t%s
            Current task count: %d
            """;

    public static final String COMMAND_FIND = """
            Tasks matching [%s] have been located:
            %s
            """;

    public static final String COMMAND_LIST = """
            Current task registry:
            %s
            """;

    public static final String COMMAND_MARK = """
            Task marked as completed:
            \t%s
            """;

    public static final String COMMAND_UNMARK = """
            Task reverted to incomplete status:
            \t%s
            """;

    public static final String COMMAND_TODO = """
            Task addition executed:
            \t%s
            Current task count: %d
            """;

    public static final String COMMAND_UNDO = """
            Undo successful. Previous action reverted:
            \t%s
            Current task count: %d
            """;

    public static final String ERROR_UNKNOWN_COMMAND = "Command not recognized. Please issue a valid command.";

    public static final String ERROR_LOAD_SAVE_FILE = "Save file load failed. Verify file integrity.";

    public static final String ERROR_SAVE_SAVE_FILE = "Save operation failed. Check write permissions.";

    public static final String ERROR_INDEX_MISSING = "Task index missing. Specify an index.";

    public static final String ERROR_PARSE_INDEX = "Provided index is invalid. Must be a number.";

    public static final String ERROR_UNKNOWN_FLAG = "Unknown flag detected: %s";

    public static final String ERROR_DUPLICATE_FLAG = "Duplicate flag detected: %s";

    public static final String ERROR_NAME_NOT_GIVEN = "Task name not provided.";

    public static final String ERROR_DEADLINE_NOT_GIVEN = "Deadline missing for this task.";

    public static final String ERROR_STARTTIME_NOT_GIVEN = "Start time missing.";

    public static final String ERROR_ENDTIME_NOT_GIVEN = "End time missing.";

    public static final String ERROR_FILTER_NOT_GIVEN = "Filter keyword required.";

    public static final String ERROR_INVALID_DATE_FORMAT = "Invalid date format detected.";

    public static final String ERROR_INVALID_INDEX = "Specified index is out of range.";

    public static final String ERROR_WRONG_TYPE_TASKSAVESTRING = "Expected task type: %s, received: %s";

    public static final String ERROR_SAVE_CORRUPTED = "Save file appears corrupted. Operation aborted.";

    public static final String ERROR_UNKNOWN_TASK_TYPE = "Unknown task type encountered: %s";

    private Messages() {
    }
}
