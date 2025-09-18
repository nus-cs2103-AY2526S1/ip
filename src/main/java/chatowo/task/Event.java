package chatowo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs at a specific time/date.
 * Extends Task with event timing functionality.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates a new event task.
     *
     * @param name Event description
     * @param from Event start date
     * @param to Event end date
     * @throws DateTimeParseException if date format is invalid
     */
    public Event(String name, String from, String to) throws DateTimeParseException {
        super(name);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Converts event to storage format string.
     *
     * @return String in format "E | isDone | description | datetime"
     */
    @Override
    public String toDataString() {
        return "E" + super.toDataString() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n(from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
