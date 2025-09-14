package john.tasks;

/**
 * Abstract representation of a task that the chatbot manages.
 */
public abstract class Task {
    private String name;
    private boolean isDone;
    /**
     * Constructor for task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    /**
     * Constructor for task with specified mark as done.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }
    /**
     * Constructor for task that creates a copy of another task.
     */
    protected Task(Task other) {
        this.name = other.name;
        this.isDone = other.isDone;
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.isDone = true;
    }
    /**
     * Unmarks a task as done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Checks whether the keyword is in the task name.
     */
    public boolean nameContains(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Creates a deep copy of the task.
     */
    public abstract Task copy();

    /**
     * String representation of the task for displaying to user.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + name;
        }
        return "[] " + name;
    }
    /**
     * String representation of the task for saving in the save file.
     */
    public String writeString() {
        int doneInt = isDone ? 1 : 0;
        return doneInt + " | " + name;
    }
}
