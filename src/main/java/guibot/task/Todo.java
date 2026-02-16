package guibot.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task.
     *
     * @param description Description of the Todo task.
     */
    private Todo(String description) {
        super(description);
    }

    /**
     * Factory method that creates an Event task from an array of strings.
     * Assumes that the number of elements in details is correct.
     *
     * @param details Array of string details to create Event task from.
     */
    public static Todo of(String... details) {
        assert details.length == 1 : "Wrong number of elements in details";
        String description = details[0];
        return new Todo(description);
    }

    @Override
    public String toStorageString() {
        return "t//" + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
