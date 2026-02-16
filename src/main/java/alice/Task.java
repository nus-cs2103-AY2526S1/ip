package alice;

/**
 * Represents the tasks that the user can add to list
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return the status of the task, whether it is completed or not. "X" for completed, " " for not completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    /**
     * Marks the task as done
     */
    public String markDone() {
        this.isDone = true;
        String description = this.getDescription().stripTrailing();
        return String.format("Nice! I've marked this task as done:\n[X] %s", description);
    }

    /**
     * Marks the task as not done
     */
    public String markUndone() {
        this.isDone = false;
        String description = this.getDescription().stripTrailing();
        return String.format("OK, I've marked this task as not done yet:\n[ ] %s", description);
    }

    /**
     *
     * @return String in the format to be written to the file
     */
    public abstract String toFileFormat();

    /**
     *
     * @return String format for the task
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), this.getDescription());
    }
}
