package aqua.task;

import aqua.command.parser.PriorityParser;
import aqua.exception.InvalidArgumentException;

/**
 * Represents a ToDo task with a description.
 */
public class ToDo extends Task {
    /**
     * Creates a Todo task.
     *
     * @param description Description of the task
     */
    public ToDo(String description) throws InvalidArgumentException {
        super(description);
    }

    /**
     * Creates a Todo task with priority.
     *
     * @param description Description of the task
     * @param priority    Priority of the task
     */
    public ToDo(String description, String priority) throws InvalidArgumentException {
        super(description);
        this.priority = PriorityParser.parse(priority);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toStorageString() {
        int priorityOrdinal = this.priority == null ? -1 : this.priority.ordinal();
        return String.format("T | %d | %d | %s", this.isDone ? 1 : 0, priorityOrdinal, this.description);
    }
}
