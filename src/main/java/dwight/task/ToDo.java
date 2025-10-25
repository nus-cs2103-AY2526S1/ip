package dwight.task;

/**
 * Represents a task without any date or time constraints. A {@code ToDo} is identified only by its
 * description.
 */
public class ToDo extends Task<ToDo> {

    /**
     * Creates a new {@code ToDo} task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getUniqueKey() {
        return "T:" + super.getUniqueKey();
    }

    /**
     * Returns a string representation of this task for display purposes. The string includes the
     * task type identifier {@code [T]}.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a serialized representation of this task suitable for saving to storage. The format
     * begins with the task type identifier {@code T}.
     *
     * @return The serialized string of the task.
     */
    @Override
    public String serialize() {
        return "T | " + super.serialize();
    }
}
