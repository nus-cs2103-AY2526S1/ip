package shef.task;

/**
 * Represents event tasks.
 */
public class EventTask extends Task {
    private String start;
    private String end;

    public EventTask(String description, String start, String end) {
        this(description, false, start, end);
    }

    /**
     * Returns a EventTask object.
     * @param description task description.
     * @param isDone denotes whether the task is done.
     * @param start start time of the task.
     * @param end end time of the task.
     */
    public EventTask(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toCsvString() {
        return String.format("E,%s,%s,%s", super.toCsvString(), start, end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }
}
