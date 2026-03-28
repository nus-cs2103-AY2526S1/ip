package bugsbunny.tasks;

import java.time.LocalDateTime;

import bugsbunny.parsers.DateTimeParser;

/**
 * A time-bounded task with a start and end.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Returns an instance of Event class.
     *
     * @param description Task name.
     * @param from Start date-time.
     * @param to End date-time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, from, to);
    }

    /**
     * Returns an instance of Event class.
     * Used only by {@link #convertFromStorageFormat(String)}.
     *
     * @param description Task name.
     * @param isDone Completion status.
     * @param from Start date-time.
     * @param to End date-time.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        assert from != null : "Start date cannot be null";
        assert to != null : "End date cannot be null";
        this.from = from;
        this.to = to;
    }

    @Override
    public String convertToStorageFormat() {
        return String.format(
                "E | %s | %s | %s",
                super.convertToStorageFormat(),
                this.from.format(DateTimeParser.DATE_TIME_STRING_FORMATTER),
                this.to.format(DateTimeParser.DATE_TIME_STRING_FORMATTER));
    }

    @Override
    public boolean isDueBy(LocalDateTime dateTime) {
        return !this.from.isAfter(dateTime);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s]%s (from: %s to: %s)",
                "E",
                super.toString(),
                this.from.format(DateTimeParser.DATE_TIME_STRING_FORMATTER),
                this.to.format(DateTimeParser.DATE_TIME_STRING_FORMATTER));
    }
}
