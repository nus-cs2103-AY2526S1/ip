package eureka.task;

/**
 * Represents a simple todo task without any specific timing constraints.
 * A todo is the most basic type of task that only has a name and completion status.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo with the specified task name and completion status.
     *
     * @param taskName the name/description of the todo task
     * @param isDone whether the todo task is marked as completed
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String serialise() {
        return "T | " + super.serialise();
    }
}
