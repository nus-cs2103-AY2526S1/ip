package seedu.duke.task;

/**
 * A simple to-do task without dates.
 */
public class ToDo extends Task {

    /**
     * Creates a {@code ToDo}.
     *
     * @param name Description of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the save type code for todos.
     *
     * @return {@code "T"}.
     */
    @Override
    protected String typeCode() {
        return "T";
    }

    /**
     * Returns a user-friendly description of this todo.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
