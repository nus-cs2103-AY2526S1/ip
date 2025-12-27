package ip.tasks;

import ip.exceptions.UnknownInputException;

/**
 * Represents a task which has a description and
 * a record of completion
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a task
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the status icon of the task based
     * on isDone
     *
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Formats the task into a String to be stored in the
     * data file
     *
     * @return "Task type (1 char) / done (1 or 0) / description"
     */
    public abstract String toDataString();

    /**
     * Snoozes a task based on the input
     */
    public abstract void snooze(String[] splitInputs) throws UnknownInputException;

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
