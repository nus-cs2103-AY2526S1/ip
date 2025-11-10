package bob.tasks;

import java.util.regex.Pattern;

/**
 * Abstract base class representing a generic task.
 */
public abstract class Task {
    private String description;
    private boolean isComplete;

    /**
     * Creates a new incomplete task with the given description.
     *
     * @param description the task description
     */
    public Task(String description) throws IllegalArgumentException{
        this(description, false);
    }

    /**
     * Creates a new task with the given description and completion status.
     *
     * @param description the task description
     * @param isComplete  whether the task is marked complete
     */
    public Task(String description, boolean isComplete) throws IllegalArgumentException{
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.description = description;
        this.isComplete = isComplete;
    }

    /** Returns the description of the task. */
    public String getDescription() {
        return description;
    }

    /** Updates the description of the task. */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /** Returns whether the task is complete. */
    public boolean isComplete() {
        return isComplete;
    }

    /** Marks the task as complete. */
    public void mark() {
        isComplete = true;
    }

    /** Marks the task as incomplete. */
    public void unmark() {
        isComplete = false;
    }

    /** Returns the type of the task (e.g., "Task", "Deadline"). */
    protected String getTaskType() {
        return "a";
    }

    /**
     * Returns true if the task description contains the given query (case-insensitive).
     *
     * @param query the search query
     */
    public boolean containsString(String query) {
        return description.matches("(?i).*" + Pattern.quote(query) + ".*");
    }

    /**
     * Returns the serialized representation of this task for storage.
     */
    public String toFileString() {
        return String.format("%s /done %d /des %s",
                getTaskType(),
                isComplete() ? 1 : 0,
                description
        );
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete() ? "X" : " ", description);
    }
}