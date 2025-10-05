package sage.command;

import java.time.LocalDateTime;

import sage.task.Deadline;

/**
 * Represents a command to add a Deadline task.
 */
public class AddDeadlineCommand extends AddCommand {
    /**
     * Constructs an AddDeadlineCommand with the given description and due date/time for the Deadline task.
     * @param description The description of the Deadline task.
     * @param by The due date and time of the Deadline task.
     */
    public AddDeadlineCommand(String description, LocalDateTime by) {
        super(new Deadline(description, by));
    }
}
