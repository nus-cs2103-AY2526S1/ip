package dino;

/**
 * Represents a Event type task with task description, task start and end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event task with the given description, start and end time.
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the Event task
     *
     * @return string containing the task type, status and description
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns the string representation of the Event task for storage.
     *
     * @return string of the task in the storing format
     */
    @Override
    public String toStoreFormat() {
        String tagName = this.tag != null ? this.tag : "";
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end + " | " + tagName;
    }

}
