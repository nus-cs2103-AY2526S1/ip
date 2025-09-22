package chash.task;

/**
 * Represents a generic task in the CHASH application.
 * This class is meant to be inherited by specific task types
 * such as {@link Todo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a new task with the specified description.
     *
     * @param description the description of the task
     */
    protected Task(String description) {
        assert description != null;

        this.description = description;
        this.isDone = false;
    }

    //Subclasses can override this to return a more specific Task (same for setDone)
    /**
     * Toggles the done status of this task.
     *
     * @return this task with updated status
     */
    public Task toggleDone() {
        this.isDone = (this.isDone) ? false : true;
        return this;
    }

    /**
     * Sets the done status of this task.
     *
     * @param status true if marked done, false otherwise
     * @return this task with updated status
     */
    public Task setDone(boolean status) {
        if (this.isDone != status) {
            this.isDone = status;
        }
        //can raise an error if necessary using else here
        return this;
    }

    /**
     * Converts the task into an exportable string format for persistence.
     *
     * @return Export string
     */
    public String exportString() {
        //Note: This string may have to be base64 encoded to avoid possible delimiter issues
        return String.format(
                "%d | %s",
                //CHECKSTYLE.OFF: SeparatorWrap
                (this.isDone) ? 1 : 0,
                //CHECKSTYLE.ON: SeparatorWrap
                this.description
        );
    }

    /**
     * Returns a human-readable representation of the task.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format(
                "[%c] %s",
                //CHECKSTYLE.OFF: SeparatorWrap
                (this.isDone) ? 'X' : ' ',
                //CHECKSTYLE.ON: SeparatorWrap
                this.description
        );
    }
}
