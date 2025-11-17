package bin.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline type of task.
     */
    public Deadline(Boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = super.readDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(super.stringFormat) + ")";
    }

    @Override
    public String addTask() {
        int num = isDone ? 1 : 0;
        return "D | " + num + " | " + description + "| " + by.format(super.format);
    }
}
