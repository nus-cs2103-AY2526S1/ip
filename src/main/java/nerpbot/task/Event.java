package nerpbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from        The start date in YYYY-MM-DD format.
     * @param to          The end date in YYYY-MM-DD format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Converts the event task to a string format suitable for saving to a file.
     *
     * @return The string representation of the event task for saving.
     */
    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + from.toString() + " | " + to.toString();
    }

    /**
     * Returns the string representation of the event task for display.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
