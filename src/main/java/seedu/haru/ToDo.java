package seedu.haru;

/**
 * Represents a ToDo task in the Haru chatbot.
 * An ToDo task has a name, completion status and type.
 */
public class ToDo extends Task {
    /**
     * Creates a new Task with the given description and type.
     *
     * @param name name of the task
     * @param type type of the task
     */
    public ToDo(String name, Type type) {
        super(name, type);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the task to a format suitable for saving in storage.
     *
     * @return a string representation of the task
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
