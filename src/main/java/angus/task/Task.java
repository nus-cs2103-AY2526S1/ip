package angus.task;

/**
 * Represents an abstract task for the task management system.
 * <p>
 * A task will have a description and completion status.
 * The concrete classes that extend this class include the Deadline, Event and Task classes,
 * which implements their own storage and display format.
 */
public abstract class Task {
    protected final String description;
    private boolean isDone;

    /**
     * Enumeration of the supported task types, which are abbreviated.
     */
    public enum TaskTypes {
        E,
        T,
        D,
    }

    /**
     * Constructs a new Task with the given description.
     * The task will initially be not done.
     * @param description A short description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the content of the task to a string for saving.
     * @return The string version of the task.
     */
    public abstract String saveTask();

    /**
     * Marks the task as done if it is not yet marked as done.
     * @return true if the task was successfully marked as done, otherwise false.
     */
    public boolean markDone() {
        if (this.isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    /**
     * Marks the task as not done (unmarks the task) if it is not done.
     * @return true if the task was successfully unmarked, otherwise false.
     */
    public boolean markNotDone() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getCompleteStatus() {
        return (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
