package haru.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Base class for all tasks.
 */
public abstract class Task implements Serializable {
    private final String name;
    private final TaskType type;
    private final ArrayList<String> tags = new ArrayList<>();
    private boolean isDone = false;

    /**
     * Constructs a Task with name and type.
     *
     * @param name the task name
     * @param type the task type
     */
    public Task(String name, TaskType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the task name.
     *
     * @return the task name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.getName();
    }

    /**
     * Adds a tag to this task.
     *
     * @param tag the tag to add
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Returns true if this task has the specified tag.
     *
     * @param tag the tag to check
     * @return true if the task contains the tag, false otherwise
     */
    public boolean hasTag(String tag) {
        return this.tags.contains(tag);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("[%c][%c] %s",
                this.type.getCode(), (this.isDone ? 'X' : ' '),
                this.getDescription()));
        if (!this.tags.isEmpty()) {
            sb.append(String.format(" (tags: %s)",
                    String.join(", ", this.tags)));
        }
        return sb.toString();
    }
}
