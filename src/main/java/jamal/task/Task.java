package jamal.task;

/**
 * Task Parent Class to Deadline, Todo and Event
 */
public class Task {

    /**
     * @param isDone to indicate true if Marked and false if Unmarked
     */
    protected boolean isDone;
    protected String description;
    protected int priority;

    /**
     * Task creation
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = 0;
    }

    /**
     * Overloaded method to instantiate a task with priority
     * @param description Task description
     * @param priority priority level
     */
    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    public String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    public int getPriority() {
        return this.priority;
    }

    /**
     * Sets the Task's priority with priority parameter
     * @param priority given by user, higher number corresponds to higher priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done / Unmarks task
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Default notation to determine if task is ongoing
     */
    public boolean isOngoing() {
        return true;
    }

    /**
     * Default notation to determine if task is upcoming
     */
    public boolean isUpcoming() {
        return false;
    }

    /**
     * Default notation to determine if task is overdue
     */
    public boolean isOverdue() {
        return false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "][P" + this.getPriority() + "] " + description;
    }
}
