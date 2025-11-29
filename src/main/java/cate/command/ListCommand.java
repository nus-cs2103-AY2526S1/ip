package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command to list all tasks in the task list.
 * This command does not modify the task list or storage.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * Retrieves all tasks from the task list and returns a formatted message using the UI.
     *
     * @param storage the storage handler (not used)
     * @param tasks   the task list to display
     * @param ui      the UI handler used to generate the task list message
     * @return a string representation of all tasks in the task list
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.printList(tasks);
    }
}
