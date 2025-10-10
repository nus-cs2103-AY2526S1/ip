package HawkerUncle.task;

/**
 * Represents a to-do task, which has a description and a status.
 */
public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the task to a format suitable for saving to storage.
     * @return A string in a format suitable for saving the task to storage.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
