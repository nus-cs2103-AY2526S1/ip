package sora.task;

/**
 * Represents a task in the Sora task management system.
 * This is the abstract base class for specific types of tasks:
 * {@code Todo}, {@code Deadline}, and {@code Event}.
 */
public class Task {
    /**
     * Enum representing the type of the task.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a {@code Task} with the given type and description.
     * By default, the task is marked as not done.
     *
     * @param type the type of task.
     * @param description the description of the task.
     */
    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a user-friendly string representation of the task.
     *
     * @return the formatted string with task type, status, and description.
     */
    public String toString() {
        String typeIcon = switch (type) {
        case TODO -> "T";
        case DEADLINE -> "D";
        case EVENT -> "E";
        };
        return "[" + typeIcon + "]" + "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns a formatted string suitable for saving to file.
     *
     * @return the formatted string for storage.
     * @throws UnsupportedOperationException if called on the wrong subclass.
     */
    public String toFormat() throws UnsupportedOperationException {
        String isDoneNumber = isDone ? "1" : "0";
        switch (type) {
        case TODO:
            return "T " + "| " + isDoneNumber + " | " + description;
        case DEADLINE:
            if (!(this instanceof Deadline)) {
                throw new UnsupportedOperationException("Not a deadline type");
            }
            String by = ((Deadline) this).byToFormat();
            return "D " + "| " + isDoneNumber + " | " + description + " | " + by;
        case EVENT:
            if (!(this instanceof Event)) {
                throw new UnsupportedOperationException("Not an event type");
            }
            String from = ((Event) this).fromToFormat();
            String to = ((Event) this).toToFormat();
            return "E " + "| " + isDoneNumber + " | " + description + " | " + from + " | " + to;
        default:
            throw new UnsupportedOperationException("Unknown type");
        }

    }
}
