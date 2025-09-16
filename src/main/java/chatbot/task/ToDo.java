package chatbot.task;

/**
 * Represents a simple task without any associated date or time.
 * <p>
 * A {@code ToDo} is one of the basic task types in the Harry chatbot.
 * It only contains a description and a completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code ToDo} task with the given description
     * and completion status.
     *
     * @param isDone      whether the task is already completed
     * @param description the description of the task
     */
    public ToDo(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the ToDo task,
     * including its type and completion status.
     * <p>
     * Format: {@code [T][X] description} or {@code [T][ ] description}
     *
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFile() {
        return "chatbot.task.ToDo||" + (isDone ? "X" : "") + "||" + description;
    }
}
