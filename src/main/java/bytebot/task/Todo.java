package bytebot.task;

import bytebot.ByteException;
import bytebot.task.Task;

/**
 * Represents a to-do task without any associated time.
 */
public class Todo extends Task {

    /**
     * Creates a to-do task with the given description.
     *
     * @param description Description of the to-do
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


