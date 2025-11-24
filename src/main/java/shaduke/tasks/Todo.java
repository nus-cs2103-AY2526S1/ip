package shaduke.tasks;

/**
 * Representation of a simple todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Reconstructs an existing todo task loaded from the storage.
     *
     * @param description the description of the task.
     * @param isDone whether the task has been done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + getClientString();
    }

    @Override
    public String store() {
        return "T" + super.store();
    }
}
