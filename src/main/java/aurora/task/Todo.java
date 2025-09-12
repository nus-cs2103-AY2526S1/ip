package aurora.task;

import java.util.List;

/**
 * Represents a Todo task.
 * Task with description and completion status.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with the given description.
     *
     * @param description the task details.
     * @param isDone The completion status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a Todo with the given description and tags.
     * Used for creating new Todo instances when reading from save file.
     *
     * @param description the task details.
     * @param isDone The completion status.
     * @param tags list of tags
     */
    public Todo(String description, boolean isDone, List<String> tags) {
        super(description, isDone);
        this.tags = tags;
    }

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toText() {
        return "T|" + super.toText() + "\n";
    }
}
