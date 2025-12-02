package som.task;

/**
 * Represents a todo task with no time constraints.
 * <p>A todo is the simplest type of task, containing only a description
 * and completion status. It does not have a due date or time.</p>
 *
 * @author Darien Tan
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task.
     *
     * @param description the task description.
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Encodes a task into a line like: T | 1 | read book.
     *
     * @return the formatted representation of the task to be saved.
     */
    public String encode() {
        return ("T | " + (this.isDone ? "1" : "0") + " | " + this.description);
    }

    /**
     * Returns string representation of the Todo task.
     *
     * @return formatted task string.
     */
    @Override
    public String toString() {
        return " [T] " + super.toString();
    }
}
