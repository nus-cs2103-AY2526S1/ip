package sofi;

/**
 * Utility class for parsing user input commands and extracting relevant information.
 * Provides static methods to parse different types of commands and their parameters.
 */
public class Parser {
    // Command constants
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_UNKNOWN = "unknown";
    
    // Command prefixes for parsing
    private static final String PREFIX_TODO = "todo";
    private static final String PREFIX_DEADLINE = "deadline";
    private static final String PREFIX_EVENT = "event";
    private static final String PREFIX_MARK = "mark";
    private static final String PREFIX_UNMARK = "unmark";
    private static final String PREFIX_DELETE = "delete";
    private static final String PREFIX_FIND = "find";
    
    // Parsing constants
    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private static final int FIND_PREFIX_LENGTH = 5;
    
    // Delimiter constants
    private static final String DEADLINE_DELIMITER = " /by ";
    private static final String EVENT_FROM_DELIMITER = " /from ";
    private static final String EVENT_TO_DELIMITER = " /to ";
    private static final String SPACE_DELIMITER = " ";
    
    /**
     * Parses the command type from user input.
     * 
     * @param userInput the raw user input string
     * @return the command type (bye, list, todo, deadline, event, mark, unmark, delete, or unknown)
     */
    public static String parseCommand(String userInput) {
        // Identify the command type from user input
        if (userInput.equals(COMMAND_BYE)) {
            return COMMAND_BYE;
        } else if (userInput.equals(COMMAND_LIST)) {
            return COMMAND_LIST;
        } else if (userInput.startsWith(PREFIX_TODO)) {
            return COMMAND_TODO;
        } else if (userInput.startsWith(PREFIX_DEADLINE)) {
            return COMMAND_DEADLINE;
        } else if (userInput.startsWith(PREFIX_EVENT)) {
            return COMMAND_EVENT;
        } else if (userInput.startsWith(PREFIX_MARK)) {
            return COMMAND_MARK;
        } else if (userInput.startsWith(PREFIX_UNMARK)) {
            return COMMAND_UNMARK;
        } else if (userInput.startsWith(PREFIX_DELETE)) {
            return COMMAND_DELETE;
        } else if (userInput.startsWith(PREFIX_FIND)) {
            return COMMAND_FIND;
        } else {
            return COMMAND_UNKNOWN;
        }
    }

    /**
     * Extracts the description from a todo command.
     * 
     * @param userInput the todo command input
     * @return the todo description, empty string if not found
     */
    public static String parseTodoDescription(String userInput) {
        return userInput.length() >= TODO_PREFIX_LENGTH ? 
            userInput.substring(TODO_PREFIX_LENGTH).trim() : "";
    }

    /**
     * Parses a deadline command to extract description and deadline.
     * 
     * @param userInput the deadline command input
     * @return array containing [description, deadline]
     */
    public static String[] parseDeadline(String userInput) {
        String[] parts = userInput.split(DEADLINE_DELIMITER, 2);
        String description = parts[0].length() >= DEADLINE_PREFIX_LENGTH ? 
            parts[0].substring(DEADLINE_PREFIX_LENGTH).trim() : "";
        String by = parts[1].trim();
        return new String[]{description, by};
    }

    /**
     * Parses an event command to extract description, start time, and end time.
     * 
     * @param userInput the event command input
     * @return array containing [description, from, to]
     */
    public static String[] parseEvent(String userInput) {
        String[] parts = userInput.split(EVENT_FROM_DELIMITER, 2);
        String description = parts[0].length() >= EVENT_PREFIX_LENGTH ? 
            parts[0].substring(EVENT_PREFIX_LENGTH).trim() : "";
        String fromTo = parts[1];
        String[] fromToParts = fromTo.split(EVENT_TO_DELIMITER, 2);
        String from = fromToParts[0].trim();
        String to = fromToParts[1].trim();
        return new String[]{description, from, to};
    }

    /**
     * Extracts the task number from mark/unmark/delete commands.
     * 
     * @param userInput the command input
     * @return the task index (0-based)
     */
    public static int parseTaskNumber(String userInput) {
        String[] tokens = userInput.split(SPACE_DELIMITER, 2);
        return Integer.parseInt(tokens[1].trim()) - 1;
    }

    /**
     * Extracts the search keyword from a find command.
     * 
     * @param userInput the find command input
     * @return the search keyword, empty string if not found
     */
    public static String parseFindKeyword(String userInput) {
        return userInput.length() >= FIND_PREFIX_LENGTH ? 
            userInput.substring(FIND_PREFIX_LENGTH).trim() : "";
    }
}
