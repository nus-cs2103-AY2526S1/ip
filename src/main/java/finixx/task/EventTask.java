package finixx.task;

/**
 * Represents an event task with a description, completion status, optional notes, start time, and end time.
 * Extends the abstract Task class by adding start and end time fields.
 * Stores the description, completion status, notes, start time, and end time of the event task.
 */
public class EventTask extends Task {
    private String start;
    private String end;

    /**
     * Constructs an EventTask with the specified description, completion status, notes, start time, and end time.
     *
     * @param description Description of the task
     * @param isDone Completion status of the task
     * @param note Note associated with the task
     * @param start Start time of the event
     * @param end End time of the event
     */
    public EventTask(String description, boolean isDone, String note, String start, String end) {
        super(description, isDone, note);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of this EventTask for display.
     *
     * @return String representation of the EventTask
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")"
                + (getNote().isEmpty() ? "" : " <" + getNote() + ">");
    }

    /**
     * Returns the start time of this event.
     *
     * @return Start time as a string
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Returns the end time of this event.
     *
     * @return End time as a string
     */
    public String getEnd() {
        return this.end;
    }

    /**
     * Converts this EventTask into a string suitable for saving to a file.
     *
     * @return String representing the EventTask for storage
     */
    @Override
    public String toSaveFormatString() {
        return "E | " + (isDone() ? "1" : "0") + " | "
                + getDescription() + " | " + start + " | " + end  + " | "
                + getNote();
    }
}
