package burh;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Parses user input strings into commands or task objects.
 */
public class Parser {
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("^\\s+|\\s+$");
    private static final Pattern MULTIPLE_SPACES_PATTERN = Pattern.compile("\\s+");
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * Returns the Command corresponding to the first word of the input.
     *
     * @param input User input string.
     * @return The parsed Command enum.
     * @throws BurhException If the command is not recognized or input is empty.
     */
    public static Command getCommand(String input) throws BurhException {
        if (input == null || input.trim().isEmpty()) {
            throw new BurhException(BurhException.ErrorType.INVALID_COMMAND);
        }

        String normalizedInput = normalizeInput(input);
        String commandWord = normalizedInput.split("\\s+")[0].toUpperCase();

        try {
            return Command.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new BurhException(BurhException.ErrorType.INVALID_COMMAND,
                    "Try: todo, deadline, event, list, mark, unmark, delete, find, or bye");
        }
    }

    /**
     * Parses and validates a task index from the command.
     *
     * @param fullCommand The full command string (e.g., "mark 1", "delete 2").
     * @param maxIndex The maximum valid index.
     * @return The parsed 1-based index.
     * @throws BurhException If the index is missing, invalid, or out of range.
     */
    public static int parseIndex(String fullCommand, int maxIndex) throws BurhException {
        String[] commandParts = fullCommand.split("\\s+", 2);

        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BurhException(BurhException.ErrorType.MISSING_INDEX);
        }

        try {
            int index = Integer.parseInt(commandParts[1].trim());
            if (index < 1 || index > maxIndex) {
                throw new BurhException(BurhException.ErrorType.INVALID_INDEX,
                        String.format("Valid range is 1 to %d", maxIndex));
            }
            return index;
        } catch (NumberFormatException e) {
            throw new BurhException(BurhException.ErrorType.INVALID_INDEX,
                    "Please enter a valid number");
        }
    }

    /**
     * Parses a Todo task from the command string.
     *
     * @param fullCommand The full command string starting with "todo".
     * @return A new Todo task.
     * @throws BurhException If the description is empty or contains only whitespace.
     */
    public static Task parseTodoTask(String fullCommand) throws BurhException {
        String description = fullCommand.replaceFirst("^\\s*todo\\s+", "").trim();
        validateDescription(description, "todo");
        return new Todo(description);
    }

    /**
     * Parses a Deadline task from the command string.
     *
     * @param fullCommand The full command string starting with "deadline".
     * @return A new Deadline task.
     * @throws BurhException If the format is invalid, description is empty, or date is invalid.
     */
    public static Task parseDeadlineTask(String fullCommand) throws BurhException {
        String[] parts = fullCommand.split("\\s+/by\\s+", 2);

        if (parts.length != 2) {
            throw new BurhException(BurhException.ErrorType.INVALID_DEADLINE_FORMAT);
        }

        String description = parts[0].replaceFirst("^\\s*deadline\\s+", "").trim();
        validateDescription(description, "deadline");

        String by = parts[1].trim();
        validateDateTime(by);

        return new Deadline(description, by);
    }

    /**
     * Parses an Event task from the command string.
     *
     * @param fullCommand The full command string starting with "event".
     * @return A new Event task.
     * @throws BurhException If the format is invalid, description is empty, or dates are invalid.
     */
    public static Task parseEventTask(String fullCommand) throws BurhException {
        // Split into description and date parts
        String[] parts = fullCommand.split("\\s+/from\\s+", 2);
        if (parts.length != 2) {
            throw new BurhException(BurhException.ErrorType.INVALID_EVENT_FORMAT);
        }

        String description = parts[0].replaceFirst("^\\s*event\\s+", "").trim();
        validateDescription(description, "event");

        // Split date range
        String[] dateParts = parts[1].split("\\s+/to\\s+", 2);
        if (dateParts.length != 2) {
            throw new BurhException(BurhException.ErrorType.INVALID_EVENT_FORMAT);
        }

        String from = dateParts[0].trim();
        String to = dateParts[1].trim();

        validateDateTime(from);
        validateDateTime(to);

        // Validate date range
        try {
            LocalDateTime start = LocalDate.parse(from, DATE_FORMATTER).atStartOfDay();
            LocalDateTime end = LocalDate.parse(to, DATE_FORMATTER).atStartOfDay();

            if (!end.isAfter(start)) {
                throw new BurhException(BurhException.ErrorType.INVALID_DATE_RANGE);
            }
        } catch (DateTimeParseException e) {
            throw new BurhException(BurhException.ErrorType.INVALID_DATE_FORMAT);
        }

        return new Event(description, from, to);
    }

    /**
     * Parses the search keyword from a find command.
     *
     * @param fullCommand The full command string starting with "find".
     * @return The search keyword.
     * @throws BurhException If no search keyword is provided.
     */
    public static String parseFindKeyword(String fullCommand) throws BurhException {
        String keyword = fullCommand.replaceFirst("^\\s*find\\s+", "").trim();
        if (keyword.isEmpty()) {
            throw new BurhException(BurhException.ErrorType.MISSING_DESCRIPTION,
                    "Please enter a search term");
        }
        return keyword;
    }

    /**
     * Validates that a task description is not empty.
     *
     * @param description The description to validate.
     * @param commandType The type of command (for error messages).
     * @throws BurhException If the description is empty.
     */
    private static void validateDescription(String description, String commandType) throws BurhException {
        if (description.isEmpty()) {
            throw new BurhException(BurhException.ErrorType.MISSING_DESCRIPTION,
                    String.format("The description of a %s cannot be empty.", commandType));
        }
    }

    /**
     * Validates that a date-time string is in the correct format.
     *
     * @param dateTime The date-time string to validate.
     * @throws BurhException If the format is invalid.
     */
    private static void validateDateTime(String date) throws BurhException {
        try {
            LocalDateTime.parse(date + "T00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new BurhException(BurhException.ErrorType.INVALID_DATE_FORMAT,
                    "Please use the format: yyyy-MM-dd (e.g., 2023-12-31)");
        }
    }

    /**
     * Normalizes input by removing extra whitespace.
     *
     * @param input The input string to normalize.
     * @return The normalized string with single spaces between words and no leading/trailing spaces.
     */
    public static String normalizeInput(String input) {
        if (input == null) {
            return "";
        }
        // Remove leading/trailing whitespace and replace multiple spaces with single space
        return input.trim().replaceAll("\\s+", " ");
    }
}
