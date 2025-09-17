package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bob.exception.InvalidInputException;

/**
 * Tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE_SYMBOL = "[D]";
    private static final String COMMAND_KEYWORD = "deadline";
    private static final String TIME_SEPARATOR = "/by";
    
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task.
     *
     * @param description description for the task.
     * @param deadline    deadline for the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Parses the input string given into Deadline object.
     *
     * @param input should be of the format: deadline [description] /by [datetime].
     * @return a new Deadline task
     * @throws InvalidInputException if the input format is invalid or description is empty
     */
    public static Deadline parse(String input) throws InvalidInputException {
        String[] params = input.replaceFirst(COMMAND_KEYWORD, "").split(TIME_SEPARATOR);
        if (params.length != 2 || params[0].trim().isEmpty() || params[1].trim().isEmpty()) {
            throw new InvalidInputException("Invalid deadline input: " + input
                    + "\nExample of valid format: deadline return book /by 01/01/2025 1200");
        }
        try {
            LocalDateTime deadline = Task.parseDateTimeString(params[1].trim());
            return new Deadline(params[0].trim(), deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format in deadline input: " + params[1].trim()
                    + "\nExample of valid format: deadline return book /by 01/01/2025 1200");
        }
    }

    @Override
    public String toString() {
        return String.format("%s%s (due: %s)", TASK_TYPE_SYMBOL, super.toString(), Task.formatDateTime(deadline));
    }

    @Override
    public String toInputString() {
        return COMMAND_KEYWORD + " " + this.description + " " + TIME_SEPARATOR + " " + Task.toInputStringDateTime(this.deadline);
    }
}
