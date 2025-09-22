package jerome.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * Extends the abstract class Task to add a start and end time for an event.
 */
public class Event extends Task {
    private static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected LocalDateTime from;
    protected LocalDateTime to;
    /**
     * Constructs an {@code Event} with the given description and time range.
     *
     * @param description Task description.
     * @param from Start datetime in format "d/M/yyyy HHmm".
     * @param to End datetime in format "d/M/yyyy HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, inputFormat);
        this.to = LocalDateTime.parse(to, inputFormat);
    }

    /**
     * Returns the raw format of the start time of the event (d/M/yyyy HHmm)
     */
    public String getFromRaw() {
        return from.format(inputFormat);
    }

    /**
     * Returns the start time of the event as a LocalDateTime object.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the raw format of the end time of the event (d/M/yyyy HHmm)
     */
    public String getToRaw() {
        return to.format(inputFormat);
    }

    /**
     * Returns the end time of the event as a LocalDateTime object.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatus() + "] " + description
                + "(from: " + from.format(displayFormat)
                + " to: " + to.format(displayFormat) + ")";
    }

}
