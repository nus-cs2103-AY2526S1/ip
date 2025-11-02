package jimbot.tasktype;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a task that contains a description and can be completed.
 *
 * @author limjimin-nus
 */
public class Task implements Serializable {
    // Version identifier number that is stored with the serialized tasks
    @Serial
    private static final long SERIAL_VERSION_UID = 1L;
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task that has the given description.
     * Tasks constructed this way will always be not done yet.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "   ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task, including its description and status.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
