package briar.task;

/**
 * Represents an event task that can be stored in a TaskList.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates an Event task.
     * @param description Description of task.
     * @param from Start time/date of the event.
     * @param to End time/date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }

    /**
     * Returns the representation of the task to be written into a file as text.
     */
    @Override
    public String toText() {
        return "E|" + super.toText() + "|" + from + "|" + to;
    }
}
