package pingu;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the done status of the task.
     * @param status The new done status.
     */
    public void toggleStatus(boolean status) {
        this.isDone = status;
    }
    
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
}
