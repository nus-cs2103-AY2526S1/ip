package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to find a task with its name containing a specified keyword
 */

public class FindCommand extends Command {
    private String taskName;

    /**
     * Initialises the target taskName to be searched for
     * @param taskName keyword to be searched for
     */
    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Carries out the relevant tasks to find the task(s) with the specified keyword
     * @param tasks accumulated list of tasks
     * @param ui UI where notifications are displayed
     * @param storage storage system where tasks are saved
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printFind(tasks.getTasks(), taskName);
    }
}
