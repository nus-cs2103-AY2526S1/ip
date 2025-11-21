package jamal.task;

/**
 * Todo Subclass of Task.
 */
public class ToDo extends Task {

    /**
     * Todo task creation
     *
     * @param description Task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overloaded Todo task creation
     *
     * @param description Task description
     */
    public ToDo(String description, int priority) {
        super(description, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
