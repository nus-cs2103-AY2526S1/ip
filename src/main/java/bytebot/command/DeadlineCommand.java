package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.task.Deadline;
import bytebot.task.Task;
import bytebot.ui.Ui;

/**
 * Adds a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs a command to add a deadline.
     *
     * @param description Description of the deadline task
     * @param by Deadline time in input format (d/M/yyyy HHmm)
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Validates description and /by, creates the deadline, persists it, and shows feedback.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        if (description == null || description.trim().isEmpty()) {
            throw new ByteException("The description of a deadline cant be empty");
        }
        if (by == null || by.trim().isEmpty()) {
            throw new ByteException("The /by time of a deadline cant be empty");
        }
        Task task = new Deadline(description, by);
        storage.addTask(task);
        ui.showAddedTask(task, storage.getSize());
    }
}


