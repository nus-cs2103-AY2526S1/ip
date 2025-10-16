package lebron.common;

/**
 * A utility class to hold constant values used throughout the application.
 */
public final class Constants {

    // File / storage
    public static final String DEFAULT_FILE_PATH = "data/tasks.txt";
    public static final String STORAGE_SEPARATOR = "\\|"; // for splitting
    public static final String STORAGE_SEPARATOR_WRITE = "|"; // for writing
    public static final String DONE_FLAG = "1";
    public static final String NOT_DONE_FLAG = "0";

    // Task type codes
    public static final String TASK_TYPE_T = "T";
    public static final String TASK_TYPE_D = "D";
    public static final String TASK_TYPE_E = "E";

    // Command words
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    // Argument delimiters
    public static final String BY_DELIMITER = "/by";
    public static final String FROM_DELIMITER = "/from";
    public static final String TO_DELIMITER = "/to";

    // Messages used by AddCommand
    public static final String UNKNOWN_COMMAND_ERROR = "Yo — I don't recognize that play. Try another command.";
    public static final String TODO_EMPTY_ERROR = "Can't add an empty todo — give me something to work with.";
    public static final String DEADLINE_EMPTY_ERROR = "Deadline needs a description. Put in some details.";
    public static final String DEADLINE_BY_EMPTY_ERROR = "Tell me when it's due — the /by date can't be empty.";
    public static final String EVENT_EMPTY_ERROR = "Events need a description. What's the event?";
    public static final String EVENT_START_EMPTY_ERROR = "When does the event start? Give me a /from date.";
    public static final String EVENT_END_EMPTY_ERROR = "And when does it end? Give me a /to date.";
    public static final String DATE_FORMAT_ERROR = "Date format's off — use YYYY-MM-DD.";
    public static final String EVENT_FORMAT_ERROR =
            "Wrong format. Use: 'event <task> /from <start date> /to <end date>'.";

    // Success / formatting strings
    public static final String SUCCESS_MESSAGE_FORMAT =
            "Got it. I've added this task:\n" + "%s\nNow you have %d tasks on the roster. Keep balling.%n";

    // DeleteCommand messages
    public static final String INVALID_TASK_NUMBER_ERROR = "Ayo, enter valid task numbers.";
    public static final String TASK_NUMBER_OUT_OF_RANGE_ERROR = "Ayo, one or more task numbers are out of range.";
    public static final String REMOVE_TASKS_MESSAGE_FORMAT =
            "Got it. Removed these tasks:\n" + "%s\nNow you have %d tasks left. Stay focused.%n";

    // Exit
    public static final String EXIT_MESSAGE = "Alright, I'm out. Keep grinding — see you soon, champ!";

    // FindCommand messages
    public static final String EMPTY_KEYWORD_ERROR = "Don't leave the search empty — give me a keyword.";
    public static final String NO_MATCHING_TASKS_MESSAGE = "No plays matched that keyword.";
    public static final String MATCHING_TASKS_HEADER = "Here are the matching plays:\n";

    // ListCommand messages
    public static final String NO_TASKS_ERROR = "No tasks on the roster.";
    public static final String TASK_LIST_HEADER = "Here's the tasks on your roster:\n";

    // Mark/Unmark messages
    public static final String TASK_NUMBER_OUT_OF_RANGE_ERROR_SINGLE = "Ayo, task number out of range.";
    public static final String INVALID_TASK_NUMBER_ERROR_SINGLE = "Ayo, enter a valid task number.";
    public static final String MARK_TASK_MESSAGE_FORMAT = "Nice — marked this as done:\n%s%n";
    public static final String UNMARK_TASK_MESSAGE_FORMAT = "Got you — marked this as not done:\n%s%n";

    // UI messages
    public static final String UI_LOADING_ERROR = "Couldn't load tasks — starting fresh. Let's get to work.";
    public static final String UI_WELCOME = "Yo, LeBron here. What do you need me to do, champ?";
    public static final String UI_INPUT_EMPTY_ERROR = "No input detected. Say something, champ.";

    // Generic errors
    public static final String ERROR_INVALID_TASK_FORMAT = "Saved task looks corrupted.";
    public static final String ERROR_UNKNOWN_COMMAND = "Yo — I don't recognize that play. Try another command.";
    public static final String ERROR_EMPTY_FILEPATH = "File path should not be null or empty";

    // IO messages
    public static final String IO_ERROR_CREATING_FILE = "Couldn't create file: ";
    public static final String IO_ERROR_READING_FILE = "Couldn't read data file: ";
    public static final String IO_ERROR_WRITING_FILE = "Failed to write task to file: ";

    // Date / display formats
    public static final String DISPLAY_DATE_PATTERN = "MMM d yyyy";

    // Type prefixes in toString
    public static final String TYPE_PREFIX_T = "[T]";
    public static final String TYPE_PREFIX_D = "[D]";
    public static final String TYPE_PREFIX_E = "[E]";

    // Status icons
    public static final String STATUS_ICON_DONE = "X";
    public static final String STATUS_ICON_NOT_DONE = " ";
}
