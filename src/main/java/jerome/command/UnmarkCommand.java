package jerome.command;

import jerome.JeromeException;
import jerome.Storage;
import jerome.TaskList;
import jerome.Ui;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    /**
     * Marks a task in the tasks list as not done with the provided index.
     *
     * @param tasks   The task list the command operates on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save or load task data.
     * @return response message to indicate successful marking of task as not done.
     * @throws JeromeException If the command cannot be executed properly.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JeromeException {
        tasks.get(index).markAsNotDone();
        storage.save(tasks.getAll());
        return ui.showUnmark(tasks.get(index));
    }
}
