package aurora.task;

import java.time.temporal.Temporal;
import java.util.List;

import aurora.util.DateUtil;

/**
 * Represents a Deadline task.
 * Task with description, due date, and completion status.
 */
public class Deadline extends Task {

    protected Temporal by;

    /**
     * Creates a Deadline with the given description and due date.
     *
     * @param description the task details
     * @param isDone the completion status
     * @param by the task due date
     */
    public Deadline(String description, boolean isDone, Temporal by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Creates a Deadline with the given description and due date.
     *
     * @param description the task details
     * @param isDone the completion status
     * @param by the task due date
     * @param tags list of tags
     */
    public Deadline(String description, boolean isDone, Temporal by, List<String> tags) {
        super(description, isDone);
        this.by = by;
        this.tags = tags;
    }

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.prettierDate(by) + ")";
    }

    @Override
    public String toText() {
        return "D|" + super.toText() + String.format("|%s\n", by);
    }
}
