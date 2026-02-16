package jibjab;

/**
 * Utility class for parsing user input commands in the JibJab application.
 * Provides static methods to parse different types of commands and extract
 * relevant information from user input strings.
 *
 * @author niyniy123
 */
public class Parser {
    private static final String SPACE = " ";
    private static final int SPLIT_LIMIT_TWO = 2;
    private static final String DEADLINE_DELIMITER = " /by ";
    private static final String EVENT_FROM_DELIMITER = " /from ";
    private static final String EVENT_TO_DELIMITER = " /to ";
    private static final int ONE_BASED_OFFSET = 1;
    private static final String ERR_TODO_DESCRIPTION_REQUIRED = "You need to enter a task description";

    /**
     * Parses a command string by splitting it into command type and arguments.
     * The input is split at the first space, with the first part being the command
     * and the remainder being the arguments.
     *
     * @param input the raw input string containing a command and its arguments
     * @return a String array where index 0 contains the command and index 1 contains
     *         the remaining arguments (if any).
     */
    public static String[] parseCommand(String input) {
        String in = (input == null) ? "" : input.trim();
        if (in.isEmpty()) {
            return new String[]{""};
        }
        return in.split(SPACE, SPLIT_LIMIT_TWO);
    }

    /**
     * Extracts the task description from a parsed command array for a todo task.
     * Expects the description to be at index 1 of the input array.
     *
     * @param input the parsed command array where index 0 is the command and
     *              index 1 should contain the task description
     * @return the task description string
     * @throws JibJabException if no task description is provided (array has insufficient elements)
     */
    public static String parseToDo(String[] input) throws JibJabException {
        if (input.length < 2 || input[1].isBlank()) {
            throw new JibJabException(ERR_TODO_DESCRIPTION_REQUIRED);
        }
        return input[1];
    }

    /**
     * Parses a deadline task input string to separate the task description from the deadline.
     * The input format should be: "task description /by deadline"
     *
     * @param input the deadline task string containing description and deadline separated by " /by "
     * @return a String array where index 0 contains the task description and
     *         index 1 contains the deadline
     */
    public static String[] parseDeadline(String input) {
        String in = (input == null) ? "" : input.trim();
        // Allow flexible spaces around /by
        String[] parts = in.split("\\s+/by\\s+", SPLIT_LIMIT_TWO);
        return parts;
    }

    /**
     * Parses an event task input string to extract the task description, start datetime, and end datetime.
     * The input format should be: "task description /from start_datetime /to end_datetime"
     *
     * @param input the event task string containing description, start datetime, and end datetime
     * @return a String array with three elements:
     *         [0] - task description,
     *         [1] - start datetime,
     *         [2] - end datetime
     * @throws ArrayIndexOutOfBoundsException if the input format is incorrect or missing required parts
     */
    public static String[] parseEvent(String input) {
        String in = (input == null) ? "" : input.trim();
        // Allow flexible spaces around /from and /to
        String[] eventTask = in.split("\\s+/from\\s+", SPLIT_LIMIT_TWO);
        if (eventTask.length < 2) {
            return new String[]{in};
        }
        String[] fromTo = eventTask[1].split("\\s+/to\\s+", SPLIT_LIMIT_TWO);
        if (fromTo.length < 2) {
            return new String[]{eventTask[0], eventTask[1]};
        }
        return new String[]{eventTask[0], fromTo[0], fromTo[1]};
    }

    /**
     * Parses a user-provided index (1-based) and converts it to a 0-based array index.
     * Used for tasklist operations
     *
     * @param input the string representation of a 1-based index
     * @return the corresponding 0-based index for tasklist access
     * @throws NumberFormatException if the input string cannot be parsed as an integer
     */
    public static int parseIndex(String input, int tasklistSize) throws JibJabException {
        String in = (input == null) ? "" : input.trim();
        String idxStr;
        try {
            String[] tokens = in.split("\\s+");
            // tokens[0] is command, tokens[1] should be the 1-based index
            idxStr = tokens[1];
        } catch (Exception e) {
            throw new JibJabException("Please provide a task number.");
        }
        int parsedIndex;
        try {
            parsedIndex = Integer.parseInt(idxStr) - ONE_BASED_OFFSET;
        } catch (NumberFormatException nfe) {
            throw new JibJabException("Please provide a valid task number.");
        }
        if (parsedIndex < 0 || parsedIndex >= tasklistSize) {
            throw new JibJabException("That task does not exist!");
        }
        return parsedIndex;
    }
}
