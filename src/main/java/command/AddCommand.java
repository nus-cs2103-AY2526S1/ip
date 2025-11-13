package command;

import misc.TaskBotException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to add a task to the task list
 */
public class AddCommand extends Command {
    private Task addedTask;

    /**
     * Initialises the task to be added as the input task t
     * @param t task to be added
     */
    public AddCommand(Task t) {
        this.addedTask = t;
    }

    /**
     * Carries out the relevant tasks to execute the add command
     * @param tasks accumulated list of tasks
     * @param ui UI where notifications are displayed
     * @param storage storage system where tasks are saved
     * @throws TaskBotException
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskBotException {
        tasks.addTask(addedTask);
        storage.saveTasks(tasks.getTasks());
        return ui.printAddedTask(addedTask, tasks.size());
    }
}
