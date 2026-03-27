package seedu.mani.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private String task;
    private boolean mark;
    private int count = 0;

    /**
     * Constructs a new Task with the given description, initially not marked.
     *
     * @param task
     */
    public Task(String task) {
        assert task != null : "Task description is null";
        assert !task.trim().isEmpty() : "Task description is empty";
        this.task = task;
        this.mark = false;
    }

    /**
     * Constructs a Task with the given description and specified mark status.
     *
     * @param task
     * @param mark
     */
    public Task(String task, boolean mark, int count) {
        assert task != null : "Task description is null";
        assert !task.trim().isEmpty() : "Task description is empty";
        this.task = task;
        this.mark = mark;
        this.count = count;
    }

    /**
     * Returns the number of duplicates of a task
     *
     * @return
     */
    public int count() { return this.count; }

    /**
     * Keeps track of every duplicate count every time its added.
     */
    public void sameName() { this.count++; }

    /**
     * Marks the task as done and returns its string representation.
     */
    public String markTask() {
        this.mark = true;
        assert this.isDone() : "Task is not marked";
        return this.toString();
    }

    /**
     * Marks the task as not done and returns its string representation.
     */
    public String unmarkTask() {
        this.mark = false;
        assert !this.isDone() : "Task is not unmarked";
        return this.toString();
    }

    /**
     * Returns a formatted string for storage purposes.
     */
    public String getDetails() {
        String status = mark ? "1" : "0";
        return status + " | " + task + " | " + this.count;
    }

    /**
     * Returns the completion status of the task.
     */
    public boolean isDone() {
        return this.mark;
    }

    /**
     * Returns task description.
     *
     * @return
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Edits the name of the task.
     *
     * @param taskDescription
     */
    public void editTask(String taskDescription) {
        this.task = taskDescription;
    }

    /**
     * Returns a user-friendly string representation of the task.
     */
    @Override
    public String toString() {
        if (mark) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
