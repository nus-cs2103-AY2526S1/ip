package aqua.task;

import java.time.temporal.Temporal;

import aqua.command.parser.PriorityParser;
import aqua.exception.InvalidArgumentException;
import aqua.time.Date;

/**
 * Represents a Deadline task with a description and a deadline.
 */
public class Deadline extends Task {
    private Temporal by;

    /**
     * Creates a Deadline Task.
     *
     * @param description description of task
     * @param by deadline of the task
     * @throws InvalidArgumentException If the description or deadline is empty
     */
    public Deadline(String description, String by) throws InvalidArgumentException {
        super(description);

        if (by.isBlank()) {
            throw new InvalidArgumentException("Deadline (/by) cannot be empty");
        }

        this.by = Date.parse(by);
    }

    /**
     * Creates a Deadline task with priority.
     *
     * @param description description of task
     * @param by deadline of the task
     * @param priority priority of the task
     * @throws InvalidArgumentException If the description, deadline or priority is empty or invalid
     */
    public Deadline(String description, String by, String priority) throws InvalidArgumentException {
        this(description, by);
        this.priority = PriorityParser.parse(priority);
    }

    @Override
    public String toString() {
        String deadlineString;
        try {
            deadlineString = Date.toDateString(by);
        } catch (InvalidArgumentException e) {
            deadlineString = "";
        }

        return String.format("[D]%s (by: %s)", super.toString(), deadlineString);
    }

    @Override
    public String toStorageString() {
        int priorityOrdinal = this.priority == null ? -1 : this.priority.ordinal();
        return String.format("D | %d | %d | %s ^ %s", this.isDone ? 1 : 0, priorityOrdinal, this.description, this.by);
    }
}
