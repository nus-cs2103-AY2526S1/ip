package charsiew.task;

/**
 * Represents a Todo task with only a name and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given name.
     *
     * @param name The description of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return A string in the format "[T][status] name".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
