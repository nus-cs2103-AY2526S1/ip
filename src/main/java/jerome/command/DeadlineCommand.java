package jerome.command;

import jerome.JeromeException;
import jerome.task.Deadline;
import jerome.task.Task;
import jerome.Storage;
import jerome.TaskList;
import jerome.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds Deadline task to task list and saves updated task list to storage.
     *
     * @param tasks   The task list the command operates on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save or load task data.
     * @return response message to indicate successful addition to be shown in the gui.
     * @throws JeromeException If the command cannot be executed properly.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JeromeException {
        try {
            Task task = new Deadline(description, by);
            tasks.add(task);
            storage.save(tasks.getAll());
            return ui.showAddition(task, tasks);
        } catch (DateTimeParseException e) {
            throw new JeromeException("Please provide a valid date in this format yyyy-mm-dd");
        }
    }
}
