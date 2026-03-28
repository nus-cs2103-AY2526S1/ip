package peanutbutter.tasks;

import peanutbutter.TaskType;

/**
 * Represents a Todo task.
 * <p>
 * A Todo is a simple task with only a description and no associated date or time
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the Todo task
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the Todo task for display.
     *
     * @return a formatted string representing the Todo task
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
