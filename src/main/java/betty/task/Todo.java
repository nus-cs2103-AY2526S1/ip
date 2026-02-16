package betty.task;

/**
 * Represents a Todo task inherited from the task class
 * A todo is a simple task with a description and completion status
 *
 * @see betty.task.Task
 */
public class Todo extends Task {

    /**
     * Construct a new Todo Task with the given description and isDone status
     *
     * @param description the details of the task
     * @param isDone      whether the task as been completed
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    /**
     * Returns the string representation of the task for display purposes
     * @return a formatted string with the task completion status and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns the string representation of the task for storage saving purposes
     * @return a formatted string with the task completion status and description for saving into storage
     */
    @Override
    public String toSaveString() {
        String doneValue = super.isDone() ? "1" : "0";
        return "T | " + super.toSaveString();
    }
}
