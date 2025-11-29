package cate.task;

/**
 * Represents a task that occurs during a specific time range.
 * An {@code Event} has a description, completion status,
 * a start time, and an end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an {@code Event} task with the given description, start time, and end time.
     *
     * @param description The description of the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Converts this {@code Event} task into a machine-readable string
     * for saving to a file. The format is:
     * <pre>
     * E,{doneFlag},{description},{start},{end}
     * </pre>
     * where {@code doneFlag} is 1 if completed, 0 if not.
     *
     * @return The string representation of this task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("E,%d,%s,%s,%s", isDone ? 1 : 0, description, start, end);
    }

    /**
     * Returns a human-readable string representation of this {@code Event}.
     * The format is:
     * <pre>
     * [E] {taskString} (from: {start} to: {end})
     * </pre>
     * where {@code taskString} comes from {@link Task#toString()}.
     *
     * @return A string representation of this task suitable for display.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), start, end);
    }
}
