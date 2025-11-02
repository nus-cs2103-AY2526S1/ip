package olaf.tasks;

import java.util.Objects;

import olaf.TaskType;

/**
 * Represents a generic task with description, completion status and a type(TODO, DEADLINE, EVENT)
 * Base class for all specific task types
 */
public class Task {

    protected boolean isDone;
    protected String description;
    protected TaskType type;

    /**
     * Constructs a new Task with the specified description and task type.
     *
     * @param desc Description of the task
     * @param type Type of the task
     */
    public Task(String desc, TaskType type) {
        this.description = desc;
        this.isDone = false;
        this.type = type;
        assert this.description != null : "Description should not be null";
        assert this.type != null : "Task type should not be null";

    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     * This method inherits the base description from {@link Object#toString()}.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String typeStr = switch (type) {
            case TODO -> "T";
            case DEADLINE -> "D";
            case EVENT -> "E";
        };
        return "[" + typeStr + "]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        return description.equals(other.description) && type == other.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type);
    }
}
