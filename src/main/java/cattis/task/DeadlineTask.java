package cattis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cattis.exception.CattisException;
import cattis.exception.CattisInvalidTimeException;

/**
 * Represents a task with <code>deadline</code>.
 */
public class DeadlineTask extends Task {
    public static final String ICON = "[D]";
    private static final String PREFIX_DEADLINE = "/by";
    private static final String NO_DEADLINE_MSG = "[No deadline]";
    private LocalDate deadline;

    DeadlineTask(String taskName, String deadline) throws CattisException {
        super(taskName);
        setTime(deadline);
    }

    /**
     * Constructor for {@code DeadlineTask} with specific status
     * primarily used for loading tasks from the file
     * @param taskName task name
     * @param deadline task deadline
     * @param status mark or unmark
     */
    DeadlineTask(String taskName, String deadline, boolean status) throws CattisException {
        super(taskName);
        setTime(deadline);
        if (status) {
            this.mark();
        } else {
            this.unmark();
        }
    }

    /**
     * Decodes a serialized task string into a <code>DeadlineTask</code> object.
     * The payload must follow the format:
     * [taskName] /by [deadline]
     * The deadline must be in the format of <code>YYYY-MM-DD</code>
     *
     * @param prompt the encoded task string
     * @return the decoded <code>DeadlineTask</code> object
     * @throws CattisException if the prompt cannot be parsed
     */
    public static DeadlineTask createFromPrompt(String prompt) throws CattisException {
        String[] parts = prompt.split(PREFIX_DEADLINE, 2);
        if (parts.length != 2) {
            throw new CattisException(CattisException.INCORRECT_FORMAT_DEADLINE);
        }
        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new CattisException(CattisException.EMPTY_FIELD);
        }
        return new DeadlineTask(parts[0].trim(), parts[1].trim());
    }

    /**
     * Check whether the task has equal date with target task
     * @param date target date
     * @return comparison result
     */
    public boolean isEqualDate(LocalDate date) {
        return this.deadline.equals(date);
    }

    @Override
    public String toEncodedString() {
        return ICON + Task.SPLITTER
                + super.getStatusIcon()
                + Task.SPLITTER + super.getTaskName() + Task.SPLITTER
                + encodeDeadline();
    }

    @Override
    public String toString() {
        return ICON + super.toString() + String.format(
                " (by: %s)", getDeadline());
    }

    public void setTime(String deadline) throws CattisException {
        try {
            var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
            this.deadline = LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException err) {
            throw new CattisInvalidTimeException(DATE_TIME_INPUT_FORMATTER);
        }
    }

    public String getDeadline() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMATTER);
        return this.deadline == null
                ? NO_DEADLINE_MSG
                : this.deadline.format(formatter);
    }

    /**
     * Format {@code this.deadline} as string based on input formatter
     * @return encoded deadline
     */
    public String encodeDeadline() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
        return this.deadline == null
                ? NO_DEADLINE_MSG
                : this.deadline.format(formatter);
    }
}
