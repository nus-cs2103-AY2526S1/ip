package marquess.task;

import marquess.exception.InsufficientParametersException;
import marquess.exception.MarquessException;

/**
 * Parent class for types of tasks that can be tracked by Marquess.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task. isDone is initialised as false.
     *
     * @param description Description of task.
     * @throws MarquessException If description is empty.
     */
    public Task(String description) throws MarquessException {
        if (description.isEmpty()) {
            throw new InsufficientParametersException("task requires - description");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for task.
     *
     * @param isDone Starting isDone status.
     * @param description Description of task.
     * @throws MarquessException If description is empty.
     */
    public Task(boolean isDone, String description) throws MarquessException {
        if (description.isEmpty()) {
            throw new InsufficientParametersException("task requires - description");
        }
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Gets the status icon as String.
     *
     * @return Status icon. Either X if complete or " " if not complete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a String representing the task in the format for storage.
     *
     * @return Comma-separated string containing data related to the task
     */
    public String exportTask() {
        return String.format("%d,%s", isDone ? 1 : 0, description);
    }

    /**
     * Returns a boolean representing if task's description contains input string.
     *
     * @param s String to match.
     * @return True if description contains s. Else false.
     */
    public boolean contains(String s) {
        return description.contains(s);
    }
}
