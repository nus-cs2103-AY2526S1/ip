package bubbles;

/**
 * Represents a task in the Bubbles chatbot.
 * A {@code Task} contains a description and a done status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "[X]" if the task is done and "[ ]" if it is not done.
     *
     * @return a string based on the task's completion status
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void setDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone(){
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }
    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    @Override
    public String toString(){
        return this.description;
    }
}
