package paul.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An event task for Paul.
 * Includes a from date and to date, in MMM dd yyyy format.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event task from the description, from date and to date.
     *
     * @param description The description of the task.
     * @param from The start date in yyyy-mm-dd format.
     * @param to The end date in yyyy-mm-dd format.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of an event in MMM dd yyyy format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the string representation of an event for saving into a file.
     *
     * @return The event task string for saving.
     */
    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + " | " + from + " | " + to;
    }
}