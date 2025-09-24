package cortana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {

    /**
     * The starting date and time of the event.
     */
    private LocalDateTime from;

    /**
     * The ending date and time of the event.
     */
    private LocalDateTime to;

    /**
     * Constructs an cortana.task.Event task with the specified name, start time, and end time.
     *
     * @param name The name or description of the event.
     * @param from The LocalDateTime when the event starts.
     * @param to   The LocalDateTime when the event ends.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks equality between this Event and another object.
     * Two Event objects are considered equal if they have passed the superclass equality check,
     * and their 'from' and 'to' fields are equal.
     * @param obj
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Event event = (Event) obj;
        return from.equals(event.from) && to.equals(event.to);
    }

    /**
     * Generates a hash code for the Event object, combining the superclass hash code
     * with the hash codes of the 'from' and 'to' fields.
     * @return the computed hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to);
    }

    /**
     * Returns a string representation of the cortana.task.Event task, including task type, completion
     * status, name, start, and end times.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        // Format date/time as e.g. 05 SEP 25 0430
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy HHmm");
        String textFrom = from.format(formatter);
        String textTo = to.format(formatter);
        return "[E]" + super.toString() + " (from: " + textFrom + " to: " + textTo + ")";
    }
}
