package command;

import misc.TaskBotException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to remove a task from the task list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Initialises the target task index (to be removed)
     * @param index target task index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskBotException {
        Task removedTask = tasks.removeTask(index);
        storage.saveTasks(tasks.getTasks());
        return ui.printDeletedTask(tasks.getTasks(), removedTask);
    }
}
