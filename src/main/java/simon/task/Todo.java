package simon.task;

/**
 * Represents a task that is to be done. A <code>Todo</code> object contains only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the inputted description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task, including its type, status icon, and description.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
