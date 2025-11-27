package mikey.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;
    protected boolean tagged;

    /**
     * Initializes a Task instance
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Returns the status icon depending on whether the task is completed
     *
     * @return X if complete, blank if incomplete
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task
     *
     * @return Description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns true if task is tagged
     * @return if task is tagged
     */
    public boolean isTagged() {
        return tagged;
    }

    /**
     * Marks this task as tagged
     */
    public void markTagged() {
        tagged = true;
    }

    /**
     * Sets this task's tag
     * @param tag This task's tag
     */
    public void setTag(String tag) {
        this.markTagged();
        this.tag = tag;
    }

    /**
     * Returns a string to be stored in storage file
     *
     * @return String to be saved
     */
    public String toSaveString() {
        String result = "? | " + (isDone ? "1" : "0") + " | " + description;
        if (isTagged()) {
            return result + " | " + tag;
        }
        return result;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String result = "[" + this.getStatusIcon() + "] " + this.description;
        if (isTagged()) {
            return result + "\n       tag: " + tag;
        }
        return result;
    }
}
