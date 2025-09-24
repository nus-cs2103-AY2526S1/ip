package sofi;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract base class for all types of tasks in the SOFI application.
 * Provides common functionality for task management including status tracking and tagging.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a new Task with the given description.
     * 
     * @param description the task description
     */
    public Task(String description) {
        assert description != null : "Task description cannot be null";
        assert !description.trim().isEmpty() : "Task description cannot be empty";
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     * 
     * @return "[X]" if completed, "[ ]" if not completed
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the description of this task.
     * 
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task is completed.
     * 
     * @return true if completed, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Adds a tag to this task.
     * 
     * @param tag the tag to add
     */
    public void addTag(String tag) {
        assert tag != null : "Tag cannot be null";
        assert !tag.trim().isEmpty() : "Tag cannot be empty";
        tags.add(tag.trim());
    }

    /**
     * Removes a tag from this task.
     * 
     * @param tag the tag to remove
     */
    public void removeTag(String tag) {
        assert tag != null : "Tag cannot be null";
        tags.remove(tag.trim());
    }

    /**
     * Returns all tags for this task.
     * 
     * @return a set of all tags
     */
    public Set<String> getTags() {
        return new HashSet<>(tags);
    }

    /**
     * Checks if this task has a specific tag.
     * 
     * @param tag the tag to check for
     * @return true if the task has this tag, false otherwise
     */
    public boolean hasTag(String tag) {
        assert tag != null : "Tag cannot be null";
        return tags.contains(tag.trim());
    }

    /**
     * Returns a string representation of all tags.
     * 
     * @return string representation of tags, empty string if no tags
     */
    public String getTagsString() {
        if (tags.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append("#").append(tag).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Returns a string representation of this task.
     * 
     * @return string representation of the task
     */
    public abstract String toString();
}
