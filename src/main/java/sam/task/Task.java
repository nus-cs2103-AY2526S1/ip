package sam.task;

/**
 * Represents a task in the task management system.
 * This is the base class for all types of tasks (Todo, Deadline, Event).
 * Each task has a description and can be marked as done or not done.
 */
public class Task {
    protected final String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     * 
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // default not done
    }

    /**
     * Marks the task as done.
     */
    public void markDone() { 
        this.isDone = true; 
    }
    
    /**
     * Marks the task as not done.
     */
    public void unmark() {
         this.isDone = false; 
    }

    /**
     * Returns whether the task is marked as done.
     * 
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task type.
     * Subclasses should override this to provide their specific type indicator.
     * 
     * @return A string representing the task type (empty string for base Task class)
     */
    protected String kind() {
        return "";
    }

    /**
     * Returns a string representation of the task's completion status.
     * 
     * @return A string in the format "[X]" for done tasks or "[ ]" for undone tasks
     */
    protected String status() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    /**
     * Returns a string representation of the task.
     * 
     * @return A string containing the task type, status, and description
     */
    @Override
    public String toString() {
        return kind() + status() + " " + description;
    }
}
