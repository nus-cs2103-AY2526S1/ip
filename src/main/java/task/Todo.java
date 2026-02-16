package task;

/**
 * Represents a simple Todo task that just has a name
 */
public class Todo extends Task {
    /**
     * Todo Constructor
     *
     * @param name
     *            The name of the Todo task
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String getTaskTitle() {
        return getName();
    }

    @Override
    protected char typeChar() {
        return 'T';
    }
}
