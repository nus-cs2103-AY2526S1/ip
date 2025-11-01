package yuan.task;

/**
 * Represents a simple task without a deadline or event period.
 *
 * <p>AI-Assisted: JavaDoc suggested by ChatGPT and refined manually.</p>
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the given description and status.
     *
     * @param description the description of the task
     * @param isDone true if the task is completed, false otherwise
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task into a storage-friendly format.
     *
     * @return storage string representation
     */
    @Override
    public String toStorageFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
