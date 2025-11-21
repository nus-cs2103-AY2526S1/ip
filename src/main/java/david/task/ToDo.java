package david.task;

/**
 * A simple task that has no time attributes.
 */
public class ToDo extends Task {
    private static final String TYPE = "T";

    /**
     * @param description Description of the task todo.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TYPE, super.toString());
    }

    @Override
    public String serialize() {
        return String.format("%s | %s", TYPE, super.serialize());
    }

    @Override
    public Task copy() {
        Task t = new ToDo(this.getDescription());
        if (this.getIsDone()) {
            t.markAsDone();
        }
        return t;
    }
}
