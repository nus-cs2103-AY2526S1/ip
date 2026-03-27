package jaiden.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for event task
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructor for event task.
     *
     * @param description Description of task.
     * @param from Start date.
     * @param to End date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        assert from != null;
        assert to != null;
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for event task.
     *
     * @param description Description of task.
     * @param isDone Done status.
     * @param from Start date.
     * @param to End date.
     */
    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        assert from != null;
        assert to != null;
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if the date is between the task start date and end date.
     *
     * @param d Date.
     * @return True if the date is between the task start date and end date.
     */
    public boolean isBetween(LocalDate d) {
        assert d != null;
        return (this.from.isBefore(d) || this.from.isEqual(d)) && (this.to.isAfter(d) || this.to.isEqual(d));
    }

    @Override
    public String save() {
        return "E | " + super.save() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
