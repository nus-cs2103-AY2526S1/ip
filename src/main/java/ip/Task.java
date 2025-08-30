package ip;

/**
 * Represents a generic task with a description and a completion status.
 * This is the base class for specific task types such as Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * By default, the task is not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the type icon of the task.
     * Default is a blank space; subclasses override this method.
     *
     * @return a string representing the task type
     */
    public String getTypeIcon() {
        return " "; // default type
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
