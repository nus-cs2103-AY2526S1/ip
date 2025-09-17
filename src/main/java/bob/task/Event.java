package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bob.exception.InvalidInputException;

/**
 * Tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    private static final String TASK_TYPE_SYMBOL = "[E]";
    private static final String COMMAND_KEYWORD = "event";
    private static final String START_SEPARATOR = "/from";
    private static final String END_SEPARATOR = "/to";
    
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event task.
     *
     * @param description description of the task.
     * @param start       start time of the task.
     * @param end         end time of the task.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Parses the input string given into Event object.
     *
     * @param input should be of the format: event [description] /from [start datetime] /to [end datetime].
     * @return a new Event task
     * @throws InvalidInputException if the input format is invalid or description is empty
     */
    public static Event parse(String input) throws InvalidInputException {
        String[] params = input.replaceFirst(COMMAND_KEYWORD, "").split(START_SEPARATOR + "|" + END_SEPARATOR);
        if (params.length != 3 || params[0].trim().isEmpty() || params[1].trim().isEmpty()
                || params[2].trim().isEmpty()) {
            throw new InvalidInputException("Invalid event input: " + input
                    + "\nExample of valid format: event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        }

        try {
            LocalDateTime start = Task.parseDateTimeString(params[1].trim());
            LocalDateTime end = Task.parseDateTimeString(params[2].trim());
            return new Event(params[0].trim(), start, end);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format in event input: " + input
                    + "\nExample of valid format: event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        }
    }

    @Override
    public String toString() {
        return String.format("%s%s (%s - %s)", TASK_TYPE_SYMBOL, super.toString(), formatDateTime(start),
                formatDateTime(end));
    }

    @Override
    public String toInputString() {
        return COMMAND_KEYWORD + " " + this.description + " " + START_SEPARATOR + " " + toInputStringDateTime(this.start) + " " + END_SEPARATOR + " "
                + toInputStringDateTime(this.end);
    }
}
