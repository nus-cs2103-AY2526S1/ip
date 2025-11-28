package nailongbot.task;

import java.time.LocalDateTime;

/**
 * Represents a simple to-do task without a specific date or time.
 * A Todo is one of the basic types of tasks. It only contains a
 * description and completion status. Since it has no associated date,
 * the getDate() method returns LocalDateTime
 * as a placeholder.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public LocalDateTime getDate() {
        return LocalDateTime.MAX;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
