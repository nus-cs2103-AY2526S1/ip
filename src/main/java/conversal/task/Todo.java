package conversal.task;

/**
 * Represents a Todo task in the Conversal chatbot.
 *
 * A Todo is a task that has only a description
 * with no date or time.
 * It is displayed with the task type symbol [D]
 *
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the string representation of this Todo task.
     * Inherits the default Task string format.
     *
     * @return the string form of the Todo task
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
