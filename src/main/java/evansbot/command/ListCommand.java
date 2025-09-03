package evansbot.command;

import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

/**
 * Represents a command to list the TaskList.
 * When executed, this command Lists the TaskList.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list the TaskList.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user (not used in this command).
     * @param storage Storage used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
