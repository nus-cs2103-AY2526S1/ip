package bob.util;

import bob.tasks.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles parsing and execution of update commands for tasks.
 */
public class Updater {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final Pattern DES_PATTERN = Pattern.compile("^update (\\d+) /des (.+)$");
    private static final Pattern BY_PATTERN = Pattern.compile("^update (\\d+) /by (.+)$");
    private static final Pattern EVENT_PATTERN = Pattern.compile("^update (\\d+) /from (.+) /to (.+)$");

    /**
     * Parses and executes an update command on the given task list.
     *
     * @param input Full input string (e.g. "update 2 /des New description")
     * @param tasks TaskList to modify
     * @return result of the update attempt
     */
    public static String handleUpdate(String input, TaskList tasks) {
        Matcher m;

        if ((m = DES_PATTERN.matcher(input)).matches()) {
            int index = Integer.parseInt(m.group(1));
            String newDesc = m.group(2);
            return tasks.updateDescription(index, newDesc);

        } else if ((m = BY_PATTERN.matcher(input)).matches()) {
            int index = Integer.parseInt(m.group(1));
            String newDueDateString = m.group(2);
            try {
                LocalDate newDueDate = LocalDate.parse(newDueDateString, DATE_FORMAT);
                return tasks.updateDeadline(index, newDueDate);
            } catch (DateTimeParseException e) {
                return "Invalid date format, use yyyy-MM-dd";
            }

        } else if ((m = EVENT_PATTERN.matcher(input)).matches()) {
            int index = Integer.parseInt(m.group(1));
            String newFromString = m.group(2);
            String newToString = m.group(3);
            try {
                LocalDate newFrom = LocalDate.parse(newFromString, DATE_FORMAT);
                LocalDate newTo = LocalDate.parse(newToString, DATE_FORMAT);
                return tasks.updateEvent(index, newFrom, newTo);
            } catch (DateTimeParseException e) {
                return "Invalid date format, use yyyy-MM-dd";
            }
        }

        return """
                Usage:
                update <index> /des <new description>
                update <index> /by <new deadline>
                update <index> /from <new start> /to <new end>
                """;
    }
}
