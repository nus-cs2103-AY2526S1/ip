package abang.task;

import java.util.Objects;
/**
 * Represents a generic task with a description and completion status.
 * <p>
 */
public abstract class Task {
    /** Whether the task is finished (true) or not (false). */
    private boolean finished;

    /** The description of the task. */
    private String taskDescription;

    private String tag;

    /**
     * Converts the task into a format suitable for file saving.
     *
     * @return the string representation of the task in file format
     */
    public abstract String toFileFormat();


    /**
     * Constructs a task with the given description.
     * By default, the task is marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        assert description != null : "Task description must not be null";
        assert !description.isBlank() : "Task description must not be blank";

        this.finished = false;
        this.taskDescription = description;

    }

    /**
     * Gets the Tag descriptiton
     *
     * @return Tag description
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Tags task with description in argument
     *
     * @param tag
     */
    public void tag(String tag) {
        this.tag = "#" + tag;
    }

    /**
     * Gets the description of the task.
     *
     * @return the task description
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Marks the task as completed.
     */
    public void done() {
        this.finished = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void notDone() {
        this.finished = false;
    }

    /**
     * Returns a string representation of the task,
     * showing its status icon and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return (this.finished == true ? "[X] " : "[ ] ") + String.format("[%s]", Objects.isNull(this.tag) ? "" : this.tag ) + taskDescription;
    }

    /**
     * Returns the status of the task as icon for file storage.
     */
    public String getStatusIcon() {
        return (finished ? "1" : "0");
    }
}
