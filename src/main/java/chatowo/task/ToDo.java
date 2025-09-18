package chatowo.task;

/**
 * Represents a basic todo task without date/time.
 * Simplest form of Task in the system.
 */
public class ToDo extends Task {
    /**
     * Creates a new todo task.
     *
     * @param name Task description
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Converts todo to storage format string.
     *
     * @return String in format "T | isDone | description"
     */
    @Override
    public String toDataString() {
        return "T" + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
