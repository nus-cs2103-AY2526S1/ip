package cheryl.task;

/**
 * Represents a generic task with a title and completion status.
 * Provides operations to mark/unmark the task as done and retrieve its details.
 */
public class Task {
    private final String title;
    private boolean isDone;

    /**
     * Creates a new Task with the given title.
     *
     * @param title The description/title of the task
     */
    public Task(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be blank.");
        }
        this.title = title;
        this.isDone = false;
    }


    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;

        assert this.isDone : "mark() must set isDone to true";
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;

        assert !this.isDone : "unmark() must set isDone to false";
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the title of the task.
     *
     * @return the task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return "[X] title" if the task is done, "[ ] title" otherwise
     */
    @Override
    public String toString() {
        if (title == null) {
            throw new IllegalStateException("Task title must not be null.");
        }
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}