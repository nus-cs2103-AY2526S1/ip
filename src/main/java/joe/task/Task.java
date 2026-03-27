package joe.task;

/**
 * Parent class of all tasks, defines main attributes each task must have
 */
public class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Creates a task object that has not been completed.
     * 
     * @param description Description of task.
     */
    public Task(String description) {
        assert !description.isEmpty() : "Description of a task cannot be empty";
        this.description = description;
    }

    /**
     * Creates a task object whose completion status can be specified.
     * 
     * @param description Description of task.
     * @param isDone Completion status.
     */
    public Task(String description, boolean isDone) {
        assert !description.isEmpty() : "Description of a task cannot be empty";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getNextDate() {
        return null;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[1] " + description;
        } else {
            return "[0] " + description;
        }
    }
}
