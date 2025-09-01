package aurora.task;

import java.time.temporal.Temporal;

import aurora.util.DateUtil;

/**
 * Represents an Event task.
 * Task with description, start date, end date, and completion status.
 */
public class Event extends Task {
    protected Temporal from;
    protected Temporal to;

    /**
     * Creates a Deadline with the given description and due date.
     *
     * @param description the task details
     * @param isDone the completion status
     * @param from the task start date
     * @param to the task end date
     */
    public Event(String description, boolean isDone, Temporal from, Temporal to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateUtil.prettierDate(from) + " to: " + DateUtil.prettierDate(to) + ")";
    }

    @Override
    public String toText() {
        return "E|" + super.toText() + String.format("|%s|%s\n", from, to);
    }
}
