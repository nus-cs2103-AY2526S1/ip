package siri.task;

/**
 * Class for Task
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(){
        isDone = false;
    }

    /**
     * Constructor of task
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * change the description of the task
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * change the status of the task
     * @param isDone new status
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     *
     * @return the status of the task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation of a generic task,
     * including its completion status and description.
     *
     * <p>If the task is marked as done, "[X]" is shown;
     * otherwise "[ ]" is shown.</p>
     *
     * @return a formatted string representing the generic task
     *         (e.g., "[ ] read book" or "[X] read book")
     */
    public String display() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

}
