package dobby.task;

import java.io.Serializable;

/**
 * Represents a simple to-do task without date or time.
 */
public class ToDo extends Task implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a to-do task with the specified description.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /** Returns a formatted string representation of the to-do task. */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
