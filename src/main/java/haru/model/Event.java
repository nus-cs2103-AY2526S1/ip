package haru.model;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final TaskTime from;
    private final TaskTime to;

    /**
     * Constructs an Event task with name, start time, and end time.
     *
     * @param name the task name
     * @param from the start time
     * @param to   the end time
     */
    public Event(String name, TaskTime from, TaskTime to) {
        super(name, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the task description with start and end time.
     *
     * @return the task description
     */
    @Override
    public String getDescription() {
        return String.format("%s (from %s to %s)", this.getName(), this.from, this.to);
    }
}
