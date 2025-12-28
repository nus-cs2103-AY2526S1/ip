package benn.tasks;

/**
 * Represents an abstract task in Benn the Chatbot.
 *
 * <p>A {@code Task} has a textual description and a completion status
 * (done or not done). Concrete subclasses such as {@link Todo},
 * {@link Deadline}, and {@link Event} extend this class to provide
 * additional fields and behaviors specific to each task type.</p>
 */
public abstract class Task {
    private final String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * Tasks are initially not marked as done.
     *
     * @param description the textual description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether this task is marked as done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a storage-ready representation of this task.
     *
     * <p>The format is defined by the concrete subclass
     * (e.g., {@link Todo}, {@link Deadline}, {@link Event}).</p>
     *
     * @return a string suitable for saving to the task storage file
     */
    public abstract String toStorageFormat();

    /**
     * Returns a string representation of this task, suitable for display to the user.
     *
     * <p>Includes a checkbox indicator for completion status:
     * {@code [X]} if done, {@code [ ]} if not done, followed by the description.</p>
     *
     * @return a formatted string representing this task
     */
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[X] %s", this.description);
        } else {
            return String.format("[ ] %s", this.description);
        }
    }

}
