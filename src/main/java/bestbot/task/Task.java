package bestbot.task;

import bestbot.exception.BestbotException;

/**
 * Represents a base class for tasks in the task list.
 * A task has a description and a completion status.
 */
public abstract class Task {

    /** Description of the task. */
    protected final String description;

    /** Indicates whether the task has been completed. */
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initially not marked as done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null && !description.isBlank() : "Task description should not be null or empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a status icon representing whether the task is completed.
     *
     * @return "X" if done, otherwise a blank space.
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Task in display format.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task suitable for saving to storage.
     *
     * @return The string format of the task for saving.
     */
    public abstract String toSaveFormat();

    /**
     * Parses a saved task line and creates the corresponding Task object.
     *
     * @param line Line from the save file.
     * @return Task represented by the line.
     * @throws BestbotException If the line cannot be parsed.
     */
    public static Task fromSaveFormat(String line) throws BestbotException {
        assert line != null && !line.isBlank() : "Save format line should not be null or empty";
        // Parse logic depending on saved format
        throw new BestbotException("Parsing from save not yet implemented.");
    }
}
