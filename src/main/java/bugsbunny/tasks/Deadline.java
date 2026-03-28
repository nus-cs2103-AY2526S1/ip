package bugsbunny.tasks;

import java.time.LocalDateTime;

import bugsbunny.parsers.DateTimeParser;

/**
 * A task with a due moment.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Returns an instance of Deadline class.
     *
     * @param description Task name.
     * @param by Due date-time (inclusive).
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }

    /**
     * Returns an instance of Deadline class.
     * Used only by {@link #convertFromStorageFormat(String)}.
     *
     * @param description Task name.
     * @param isDone Completion status.
     * @param by Due date-time (inclusive).
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        assert by != null : "End date cannot be null";
        this.by = by;
    }

    @Override
    public String convertToStorageFormat() {
        return String.format(
                "D | %s | %s",
                super.convertToStorageFormat(),
                this.by.format(DateTimeParser.DATE_TIME_STRING_FORMATTER));
    }

    @Override
    public boolean isDueBy(LocalDateTime dateTime) {
        return !this.by.isAfter(dateTime);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s]%s (by: %s)",
                "D", super.toString(),
                this.by.format(DateTimeParser.DATE_TIME_STRING_FORMATTER));
    }
}
