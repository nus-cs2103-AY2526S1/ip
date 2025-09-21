package merlie.command;

import java.time.LocalDateTime;

import merlie.task.Deadline;

/**
 * Adds a Deadline task to the task list.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Constructs a DeadlineCommand for the specified Deadline task.
     *
     * @param description Description of the task.
     * @param by Deadline date (and time).
     * @param hasTime Whether the deadline includes a specific time.
     */
    public DeadlineCommand(String description, LocalDateTime by, boolean hasTime) {
        super(new Deadline(description, by, hasTime));
    }
}
