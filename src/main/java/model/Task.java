package model;

/**
 * Represents a task made by user
 * Each task contains a description of the task
 * The status of task
 */
public abstract class Task {
    public String description;
    private boolean isDone;

    /**
     * Constructs a Task
     *
     * @param description  A short description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark the status of task as done
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Mark the status of task as not done
     */
    public void unmark(){
        this.isDone = false;
    }

    /**
     * Returns the status of the task, representing as string
     *
     * @return the status
     */
    public String getStatus(){
        return (isDone ? "[x]" : "[ ]");
    }
    public abstract String getType();

    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus() + " " + description;
    }
}
