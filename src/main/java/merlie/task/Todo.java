package merlie.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the given description.
     *
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean isUpdateSuccessful(Task other) {
        return false;
    }

    @Override
    public String format() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
