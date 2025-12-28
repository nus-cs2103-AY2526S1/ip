package bytebuddy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in ByteBuddy.
 * An Event has a description, a start date/time, an end date/time, and a completion status.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event with the given description and start/end times.
     *
     * @param description Description of the event.
     * @param from Start date/time in the format 'yyyy-MM-dd HHmm'.
     * @param to End date/time in the format 'yyyy-MM-dd HHmm'.
     * @param isDone true if the event is already completed; false otherwise.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, inputFormat);
        this.to = LocalDateTime.parse(to, inputFormat);
    }

    /**
     * Creates an Event with the given description and start/end times.
     *
     * @param description Description of the event.
     * @param from Start date/time as a LocalDateTime.
     * @param to End date/time as a LocalDateTime.
     * @param isDone true if the event is already completed; false otherwise.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event that is not yet completed.
     *
     * @param description Description of the event.
     * @param from Start date/time in the format 'yyyy-MM-dd HHmm'.
     * @param to End date/time in the format 'yyyy-MM-dd HHmm'.
     */
    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Appends the event's timeframe formatted as 'MMM dd yyyy HH:mm'.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " | " + from.format(outputFormat) + " to " + to.format(outputFormat);
    }
}
