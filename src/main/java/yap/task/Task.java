package yap.task;

/**
 * Abstract base class representing a generic Task.
 *
 * <p>Responsibilities: store task name and completion state, and provide core behaviours common to
 * all tasks. Collaborators: extended by ToDos, Deadlines, and Events.
 */
public class Task {

    private String name;
    private boolean isDone;

    /** Creates a new task with the given name; tasks start as not done. */
    public Task(String name) {

        assert name != null && !name.isBlank() : "Task name must be non-null, non-blank";
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns the name/description of the task.
     *
     * @return the task name
     */
    public String getName() {

        assert this.name != null : "Task name unexpectedly null";
        return this.name;
    }

    /** Returns "X" if done, otherwise a single space. */
    public String getStatusIcon() {

        return isDone ? "X" : " ";
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone the completion status to set
     */
    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return the completion status as a Boolean object
     */
    public Boolean getStatus() {
        return isDone;
    }

    /** Returns true if the task has been marked done. */
    public boolean isDone() {
        return Boolean.TRUE.equals(isDone);
    }

    /** Marks this task as completed. */
    public void markDone() {
        isDone = true;
    }

    /**
     * Sets the name of the task.
     *
     * @param name the new name for the task
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
