package alex;

/**
 * Represents a task of type event.
 * An <code>Event</code> is a {@link Task} that has a start time and an end time.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructs an <code>Event</code> with the specified description,
     * start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event (raw string as provided by user).
     * @param to End time of the event (raw string as provided by user).
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event suitable for saving to a file.
     * Format: <code>E / state / description / from to</code>
     *
     * @return File-friendly string representation of the event.
     */
    @Override
    public String toFileString() {
        return "E / " + this.doTaskState() + " / " + this.getDescription() + " / " + this.from + " " + this.to;
    }

    /**
     * Returns a string representation of the event suitable for display to the user.
     * Example: <code>[E][ ] project meeting (from: 2pm to: 4pm)</code>
     *
     * @return User-friendly string representation of the event.
     */
    @Override
    public String toString() {
        String[] fromSplit = from.split("from");
        String[] toSplit = to.split("to");
        return "[E]" + super.toString() + " (from:" + fromSplit[1] + " to:" + toSplit[1] + ")";
    }
}
