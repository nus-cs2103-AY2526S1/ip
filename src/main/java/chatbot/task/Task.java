package chatbot.task;

/**
 * Represents a task in the chatbot system.
 * A task has a description and a completion status.
 * Subclasses may represent specific types of tasks like Todo, Deadline, or Event.
 */
public class Task {

    private boolean isDone;
    private final String description;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns true if the task has been marked as done.
     *
     * @return whether the task is completed
     */
    public boolean isDone() {
        return isDone;
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
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
