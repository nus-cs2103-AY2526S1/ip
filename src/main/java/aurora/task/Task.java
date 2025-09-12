package aurora.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task with a description and completion status.
 * Abstract base class for all subtasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    /**
     * Creates a Task with the given description.
     *
     * @param description The task details.
     * @param isDone The completion status.
     */
    public Task(String description, boolean isDone) {
        assert description != null && !description.isBlank() : "Description cannot be null or empty";
        this.description = description;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string describing the task and its completion status.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (isDone) {
            result.append("[X] ");
        } else {
            result.append("[ ] ");
        }
        result.append(description);
        for (String tag : tags) {
            result.append(" #").append(tag);
        }
        return result.toString();
    }

    /**
     * Marks this task as complete.
     */
    public abstract void complete();

    /**
     * Returns a text representation of the task.
     *
     * @return A string separated by | representing the task.
     */
    public String toText() {
        StringBuilder result = new StringBuilder();
        result.append(isDone).append("|");
        result.append(description).append("|");
        for (String tag : tags) {
            result.append(tag).append(" ");
        }
        return result.toString().trim();
    }

    /**
     * Returns the description of this task.
     *
     * @return The description as a string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return A boolean representing the task completion status.
     */
    public boolean isDone() {
        return isDone;
    }
}
