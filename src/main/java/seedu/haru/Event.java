package seedu.haru;

/**
 * Represents a Event task in the Haru chatbot.
 * An Event task has a name, completion status, type, start date and end date.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a new Task with the given description, start date, end date and type.
     *
     * @param name name of the task
     * @param end end date of the task
     * @param start start date of the task
     * @param type type of the task
     */
    public Event(String name, String end, String start, Type type) {
        super(name, type);
        assert end != null : "End date string must not be null";
        assert start != null : "STart date string must not be null";
        this.end = end;
        this.start = start;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.end +  " to: " + this.start + ")";
    }

    /**
     * Converts the task to a format suitable for saving in storage.
     *
     * @return a string representation of the task
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.start + " | " + this.end;
    }
}
