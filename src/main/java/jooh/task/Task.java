package jooh.task;
/**
 * Represents the base type for all tasks in Jooh.
 * Stores a description and completion status, and provides
 * common operations for marking tasks done/undone.
 */
public class Task {
    private String desc;
    private Boolean isDone;
    /**
     * Constructs a {@code Task} with the given description and completion state.
     *
     * @param desc   Description of the task.
     * @param isDone Whether the task is marked as completed.
     */
    public Task(String desc, Boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }
    /**
     * Marks this task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }
    /**
     * Marks this task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }
    /**
     * Returns the description of this task.
     *
     * @return The task description.
     */
    public String getDesc() {
        return desc;
    }
    /**
     * Returns whether this task is completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public Boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + desc : "[ ] " + desc;
    }
}
