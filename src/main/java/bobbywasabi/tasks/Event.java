package bobbywasabi.tasks;

/**
 * Represents a task that occurs during a specific time range, with a start and end time.
 * Extends the Task class by including additional timing information.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an Event task with the given description, completion status,
     * start time, and end time.
     *
     * @param description The description of the event.
     * @param isMarked Whether the event is marked as completed.
     * @param start The starting time of the event.
     * @param end The ending time of the event.
     */
    public Event(String description, boolean isMarked, String start, String end) {
        super(description, isMarked);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string representing the duration of the event.
     *
     * @return A string in the format {@code "(from:<start>to:<end>)"}.
     */
    public String getDuration() {
        return String.format("(from:%sto:%s)",
                this.start,
                this.end);
    }

    /**
     * Returns a string representation of the event task,
     * including its type, marked status, description, and duration.
     *
     * @return Formatted string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + this.getDuration();
    }

    /**
     * Returns a serialized string of the event suitable for saving to a file.
     * Format: {@code E|<description>|<checked>|<start>|<end>}.
     *
     * @return A string representing the event for file storage.
     */
    @Override
    public String getData() {
        return String.format("E|%s|%s|%s|%s",
                super.getDescription(), super.checked(), this.start, this.end);
    }
}
