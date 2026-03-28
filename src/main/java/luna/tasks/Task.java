package luna.tasks;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a general task with a description and completion status.
 * This is the base class for more specific task types.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a Task with the given description and status.
     *
     * @param description The task description.
     * @param isDone True if the task is marked as done; false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new HashSet<>();
    };

    public boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() { this.isDone = true; }

    public void unmark() {
        this.isDone = false;
    }

    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    /**
     * Returns a string representation of the tags tagged to each task
     *
     * @return The string to represent the tags
     */

    public String tagToString() {
        return tags.isEmpty()
                ? ""
                : " | " + tags.stream().map(tag -> "#" + tag).reduce((a, b) -> a + " " + b).get();
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return The string to be written to the save file.
     */
    public String toFileString() {
        return " | " + (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
