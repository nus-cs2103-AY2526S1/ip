package itachi.task;

/**
 * Represents a ToDo task with only description. It inherits
 * common task behaviour from {@link Task}.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description
     * @param description describes the task
     */
    public Todo(String description) {
        super(description);
        assert description != null : "Description can't be null";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
