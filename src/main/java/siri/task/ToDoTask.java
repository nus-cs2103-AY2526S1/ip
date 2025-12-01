package siri.task;

/**
 * Class for TodoTask
 */
public class ToDoTask extends Task {
    /**
     * Constrcutor of todoTask
     * @param description description of task
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this todo task,
     * including its type marker "[T]", completion status,
     * and description.
     *
     * <p>If the task is marked as done, "[X]" is shown;
     * otherwise "[ ]" is shown.</p>
     *
     * @return a formatted string representing the todo task
     *         (e.g., "[T][X] borrow book" or "[T][ ] borrow book")
     */
    @Override
    public String display() {
        if (super.isDone()) {
            return "[T][X] " + super.getDescription();
        } else {
            return "[T][ ] " + super.getDescription();
        }
    }
}
