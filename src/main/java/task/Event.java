package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a time period.
 * It has a description (inherited from {@link Task}}, a start date (denoted as {@code from})
 * and an end date (denoted as {@code to}), the latter two of which are stored as {@link LocalDate}
 * objects.
 */
public class Event extends Task {

    public final LocalDate from;
    public final LocalDate to;

    /**
     * Constructs an {@link Event} task with the given description, start date and end date as strings.
     * The start and end dates are parsed into {@link LocalDate} objects.
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(dtf) + " to: " + to.format(dtf) + ")";
    }
}
