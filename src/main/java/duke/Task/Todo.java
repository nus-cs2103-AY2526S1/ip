package duke.task;

/**
 * Represents a task without any date/time (a simple todo).
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task.
     *
     * @param input the description of the task
     */
    public Todo(String input) {
        super(input);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return formatted string with task type and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
