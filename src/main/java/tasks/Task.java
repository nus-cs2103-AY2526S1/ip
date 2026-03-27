package tasks;

import amogus.AmogusException;

/**
 * Represents a Task object.
 */
public class Task {

    private final String description;
    private boolean isDone;
    private String tag;
    private boolean isTagged;

    /**
     * Creates a new Task object with a description.
     * @param description desc of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
        this.isTagged = false;
    }

    /**
     * Checks the isDone attribute of the object to check status
     * of the object
     *
     * @return 1 or 0 depending on whether the task has been completed
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * @return description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return boolean check of whether task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks task as complete.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Tags the task.
     * @param tag tag description.
     */
    public void tag(String tag) throws AmogusException {
        if (this.isTagged) {
            throw new AmogusException("Task already tagged!");
        } else {
            this.tag = tag;
            this.isTagged = true;
        }
    }

    /**
     * Gives string rep of tag
     * @return tag
     */
    public String getTag() {
        if (this.tag.isEmpty()) {
            return "";
        } else {
            return " | #" + this.tag;
        }
    }

    /**
     * Returns the type of task.
     * @return type of task.
     */
    public String getType() {
        return " ";
    }

    /**
     * Correct display of task for easier understanding.
     * @return display string of task.
     */
    public String getDisplayString() {
        String statusIcon = isDone ? "X" : " ";
        return "[" + getType() + "][" + statusIcon + "] " + description + getTag();
    }

    /**
     * @return string representation of Task
     */
    public String toString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + description + getTag();
    }

}
