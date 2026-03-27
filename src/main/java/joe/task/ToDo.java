package joe.task;

/**
 * Type of task that just contains a description
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo task that has not been completed.
     * 
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo task whose completion status can be specified.
     * 
     * @param description Description of the task.
     * @param isDone Completion status.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
