package penguin.tasks;

/**
 * A task has a description and a status reflecting on whether it is completed
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Pretty prints status of completion
     * @return status icon depending on whether it is completed
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Retrieve task description
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
