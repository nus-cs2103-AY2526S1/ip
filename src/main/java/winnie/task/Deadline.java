package winnie.task;

import java.time.LocalDateTime;
import winnie.util.DateTimeUtil;
import winnie.exception.InvalidDateTimeException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Creates a deadline task.
     *
     * @param description The description of the task.
     * @param by          The deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskEnum.DEADLINE);
        assert by != null : "Deadline date cannot be null";
        this.by = by;
    }

    /**
     * Creates a deadline task.
     *
     * @param description The description of the task.
     * @param byString    The deadline date and time as a string.
     * @throws InvalidDateTimeException If the date and time string is invalid.
     */
    public Deadline(String description, String byString) throws InvalidDateTimeException {
        super(description, TaskEnum.DEADLINE);
        this.by = DateTimeUtil.parseDateTime(byString);
    }

    /**
     * Gets the deadline date and time.
     *
     * @return The deadline date and time.
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return getTypeIcon()
                + getStatusIcon() + " " + getDescription()
                + " (by: " + DateTimeUtil.formatForDisplay(by) + ")";
    }
}
