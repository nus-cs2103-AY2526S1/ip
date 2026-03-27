package seedu.mani.task;

/**
 * Represents a task (only with description).
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param task Descirp
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Constructs a new Todo task with the specified description and completion status.
     *
     * @param task
     * @param mark
     */
    public Todo(String task, boolean mark, int count) {
        super(task, mark, count);
    }

    @Override
    public String getDetails() {
        return "T | " + super.getDetails();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
