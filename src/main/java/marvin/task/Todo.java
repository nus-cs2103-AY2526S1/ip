package marvin.task;

/**
 * A Todo object with a description.
 */
public class Todo extends Task {

    /**
     * Instantiates a Todo object.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates a Todo object.
     *
     * @param description The description of the task.
     * @param isDone      The completeness status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        // Construct string based on description and status
        return String.format("[T]%s %s", super.toString(), this.getDescription());
    }
}
