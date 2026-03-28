package com.arnavjhajharia.penguin.model.task;

/**
 * A {@link Task} representing a simple to-do item without deadlines or time ranges.
 * Stores only the description and done/undone status.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Provide a concise string representation for display.</li>
 *   <li>Provide a storage-ready line format for persistence.</li>
 * </ul>
 *
 * @since 1.0
 */
public final class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with a description and identifier.
     *
     * @param task description of the task
     * @param id   zero-based identifier within a task list
     */
    public Todo(String task, int id) {
        super(task, id);
    }

    /**
     * Returns a user-facing string representation of this todo task.
     * <p>
     * Example:
     * <pre>
     * [T] read book
     * </pre>
     *
     * @return formatted string for display
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    /**
     * Returns a storage-ready line representation of this todo task.
     * <p>
     * Format:
     * <pre>
     * T | &lt;doneFlag&gt; | &lt;description&gt;
     * </pre>
     *
     * @return formatted string suitable for persistence
     */
    @Override
    public String toStorageLine() {
        return String.format("T | %s | %s", doneFlag(), name);
    }
}
