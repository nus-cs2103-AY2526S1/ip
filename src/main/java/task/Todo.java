package task;


/**
 * Represents a todo task with a description but no date/time qualifiers.
 */
public class Todo extends Task {


    /**
     * Creates a {@code Todo} with the given description.
     *
     * @param taskName the task description
     */
    public Todo(String taskName) {
        super(taskName);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
