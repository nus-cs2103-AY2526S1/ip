package khat.task;

/** Represents an abstract Task with a description and completion status */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description Description of Task.
     * @param isDone True if Task is completed, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns completion status of the Task in the form of a string.
     *
     * @return "X" if completed, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of task for file storage.
     *
     * @return String for saving to file.
     */
    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
