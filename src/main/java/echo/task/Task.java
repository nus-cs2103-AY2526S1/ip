package echo.task;

import java.util.Map;

/**
 * Represents a task created by the user. A <code>Task</code> object
 * stores a String <code>description</code> and boolean flag <code>isDone</code>
 * indicating whether a task has been completed.
 */
public class Task {
    public static final Map<Class<? extends Task>, Integer> TASK_TYPE_MAPPING = Map.of(
            Todo.class, 0,
            Deadline.class, 1,
            Event.class, 2
    );
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the corresponding icon based on isDone boolean
     * "X" if isDone is true, " " if isDone is false
     *
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the corresponding number as a string
     * "1" if isDone is true, "0" if isDone is false
     *
     * @return String
     */
    public String getStatusNumber() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getOrder() {
        return TASK_TYPE_MAPPING.getOrDefault(this.getClass(), Integer.MAX_VALUE);
    }

    /**
     * Returns the task as a String formatted to be saved in txt file
     *
     * @return String
     */
    public String toDataString() {
        return getStatusNumber() + " | " + this.description;
    }

    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
