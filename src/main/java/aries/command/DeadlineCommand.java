package aries.command;

import aries.AriesException;
import aries.task.Deadline;
import aries.task.Task;
import aries.task.TaskList;
import aries.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand implements Command {
    private String description;

    /**
     * Constructs a DeadlineCommand with the specified deadline.
     *
     * @param description The full description of the deadline task, including /by.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui) throws AriesException {
        if (description == null || description.isEmpty()) {
            throw new AriesException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] parts = description.split(" /by ");
        if (parts.length != 2 || parts[0].isEmpty()) {
            throw new AriesException("OOPS!!! The description of a deadline must be in the format: "
                    + "description /by date_time");
        }

        String desc = parts[0].trim();
        String by = parts[1].trim();
        Task t = new Deadline(desc, by);
        tasks.addTask(t);
        return new CommandResult(ui.showAddedString(tasks), true, false);
    }
}
