package kane;

/**
 * Parses user input commands into structured objects such as {@code ToDo}, {@code Deadline}, and {@code Event}.
 * Provides helper methods to extract command words, task indexes, and task details.
 */
public class Parser {

    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_FROM_DELIMITER = "/from";
    private static final String EVENT_TO_DELIMITER = "/to";

    /**
     * Extracts the command word (the first word) from the full user command.
     *
     * @param fullCommand The raw user input.
     * @return The command word (e.g. {@code "todo"}, {@code "deadline"}, {@code "event"}).
     */
    public static String getCommandWord(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    /**
     * Parses the index of a task from a user command.
     * User input is expected to be 1-based, but internally the index is 0-based.
     *
     * @param fullCommand The raw user input.
     * @return The 0-based index of the task.
     * @throws KaneException If no index is provided or if the input is not an integer.
     */
    public static int parseIndex(String fullCommand) throws KaneException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new KaneException("OOPS!!! Please provide a task number.");
        }

        assert parts.length == 2 : "parseIndex should only be called with commands that have an index.";

        try {
            // User input is 1-based, internal is 0-based
            return Integer.parseInt(parts[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new KaneException("OOPS!!! The task number must be an integer.");
        }
    }


    /**
     * Parses a {@code ToDo} task from a user command.
     * The command should start with {@code "todo"} followed by the description.
     *
     * @param fullCommand The raw user input.
     * @return A {@code ToDo} task.
     * @throws KaneException If the description is missing or empty.
     */
    public static ToDo parseTodo(String fullCommand) throws KaneException {
        String description = fullCommand.substring(TODO_COMMAND.length()).trim();
        if (description.isEmpty()) {
            throw new KaneException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(description);
    }

    /**
     * Parses a {@code Deadline} task from a user command.
     * The command should contain {@code "/by"} to indicate the deadline time.
     *
     * @param fullCommand The raw user input.
     * @return A {@code Deadline} task.
     * @throws KaneException If the description or deadline time is missing or empty.
     */
    public static Deadline parseDeadline(String fullCommand) throws KaneException {
        if (!fullCommand.contains(DEADLINE_DELIMITER)) {
            throw new KaneException("OOPS!!! The deadline must have a /by time.");
        }
        String content = fullCommand.substring(DEADLINE_COMMAND.length()).trim();
        String[] parts = content.split(DEADLINE_DELIMITER, 2);

        String description = parts[0].trim();
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new KaneException("OOPS!!! The deadline time is missing after /by.");
        }
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new KaneException("OOPS!!! The description and deadline cannot be empty.");
        }
        return new Deadline(description, by);
    }

    /**
     * Parses an {@code Event} task from a user command.
     * The command should contain both {@code "/from"} and {@code "/to"} to indicate start and end times.
     *
     * @param fullCommand The raw user input.
     * @return An {@code Event} task.
     * @throws KaneException If the description, start time, or end time is missing or empty.
     */
    public static Event parseEvent(String fullCommand) throws KaneException {
        if (!fullCommand.contains(EVENT_FROM_DELIMITER) || !fullCommand.contains(EVENT_TO_DELIMITER)) {
            throw new KaneException("OOPS!!! The event must have both /from and /to times.");
        }
        String content = fullCommand.substring(EVENT_COMMAND.length()).trim();
        String[] parts = content.split(EVENT_FROM_DELIMITER, 2);

        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new KaneException("OOPS!!! The description cannot be empty.");
        }

        if (parts.length < 2 || !parts[1].contains(EVENT_TO_DELIMITER)) {
            throw new KaneException("OOPS!!! Missing event times after /from or /to.");
        }

        String[] timeParts = parts[1].split(EVENT_TO_DELIMITER, 2);
        String from = timeParts[0].trim();
        if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
            throw new KaneException("OOPS!!! Missing event end time after /to.");
        }
        String to = timeParts[1].trim();

        if (from.isEmpty()) {
            throw new KaneException("OOPS!!! The 'from' time cannot be empty.");
        }
        return new Event(description, from, to);
    }

    /**
     * Parses the find command to extract the search keyword.
     *
     * @param fullCommand The full user input string, e.g., "find book".
     * @return The keyword to search for.
     * @throws KaneException If the keyword is missing.
     */
    public static String parseFind(String fullCommand) throws KaneException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new KaneException("OOPS!!! The keyword for find cannot be empty.");
        }
        return parts[1].trim();
    }
}