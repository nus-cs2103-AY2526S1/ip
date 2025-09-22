package jerome.task;

/**
 * Represents a task.
 * The base class for different types of tasks (Todo, Deadline, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean isComplete;

    /**
     * Constructs a new task with the given description.
     * By default, tasks are not marked as isComplete.
     *
     * @param description The textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Marks the task as isComplete.
     */
    public void mark() {
        this.isComplete = true;
        System.out.println("Nice! I have marked the following task as complete!");
        System.out.println(this);
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void unmark() {
        this.isComplete = false;
        System.out.println("Aww! I will unmark the following task...");
        System.out.println(this);
    }

    /**
     * Returns true if the task is isComplete.
     */
    public boolean isDone() {
        return isComplete;
    }

    /**
     * Returns the description of the task.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task (x for done, space for not done).
     */
    public String getStatus() {
        return (isComplete ? "x"
                           : " ");
    }

    /**
     * Returns a string representation of the task.
     */
    public abstract String toString();
}
