package dukeychatbot.tasktypes;

/**
 * Constructs Todo class which inherits from Task class.
 * Overrides {@code toString()} method.
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     *
     * Todo {@code toString()} method includes "[T]" at the front of the string.
     */
    @Override
    public String toString() {
        return "[T] " + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
