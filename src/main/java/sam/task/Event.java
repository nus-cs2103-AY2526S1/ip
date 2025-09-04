package sam.task;

/**
 * Represents an event task in the task management system.
 * An event task has a description, completion status, start time, and end time.
 * It extends the base Task class and provides event-specific functionality.
 */
public class Event extends Task {
    private final String from, to;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     * The task is initially marked as not done.
     *
     * @param description The description of the event task
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(final String description, final String from, final String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with the given description, completion status, start time, and end time.
     *
     * @param description The description of the event task
     * @param isDone The initial completion status of the task
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(final String description, final boolean isDone, final String from, final String to) {
        super(description);
        if (isDone) {
            this.markDone();
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type indicator for Event tasks.
     *
     * @return The string "[E]" representing an Event task
     */
    @Override
    protected String kind() {
        return "[E]";
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string containing the task type, status, description, start time, and end time
     */
    @Override
    public String toString() {
        return kind() + status() + " " + description + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the raw start time string for storage purposes.
     *
     * @return The start time string as stored internally
     */
    public String getFromRaw() {
        return from;
    }

    /**
     * Returns the raw end time string for storage purposes.
     *
     * @return The end time string as stored internally
     */
    public String getToRaw() {
        return to;
    }
}
