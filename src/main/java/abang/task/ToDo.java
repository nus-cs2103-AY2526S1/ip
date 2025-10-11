package abang.task;

import java.util.Objects;

/**
 * Represents a simple todo task with only a description.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task with the given description.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo task.
     *
     * @return the string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the file format representation of this todo task,
     * used for saving to persistent storage.
     *
     * @return the todo task as a string suitable for file saving
     */
    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + getTaskDescription() + " | " + (Objects.isNull(this.getTag()) ? "null" : this.getTag().split("#")[1]);
    }

}
