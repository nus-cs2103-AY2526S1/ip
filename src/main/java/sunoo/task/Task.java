package sunoo.task;

/**
 * Represents a generic task with a description and completion state.
 */
public class Task {

    /** Whether the task is completed. */
    protected boolean isDone;

    /** Description of the task */
    protected final String description;

    /**
     * Creates a Task with a given completion state and description.
     *
     * @param isDone Whether the task is completed.
     * @param description Description of the task.
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Returns an icon that shows whether the task is completed.
     *
     * @return "[X]" if task is done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Completion status icon of the task and its description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns a string representation suitable for saving to a text file.
     * Format: " | completion(1/0) | description"
     *
     * @return String representation of the task in the txt file.
     */
    public String getTxtRepresentation() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    public boolean getCompleteStatus() {
        return isDone;
    }

    /**
     * Returns boolean value of whether the task description contains a keyword.
     *
     * @param keyword Keyword to search.
     * @return true if The task description contains the keyword.
     */
    public boolean descriptionContainsKeyword(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task t) {
            return description.equals(t.description);
        }
        return false;
    }
}
