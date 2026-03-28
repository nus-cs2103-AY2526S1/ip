package lenny.logic.command;

import lenny.logic.storage.Storage;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;

/**
 * Represents a command that lists all tasks currently in the task list.
 * Returns the tasks as a numbered list formatted for display.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand instance.
     */
    public ListCommand() {}

    /**
     * Executes the list command by iterating through the task list
     * and returning each task in a numbered format.
     *
     * @param tasks   The TaskList containing all tasks to be listed.
     * @param storage The Storage object responsible for persisting tasks
     *                (not directly used here).
     * @param ui      The Ui component used for interactions
     *                (not directly used here).
     * @return A string representation of all tasks in the task list,
     *         formatted as a numbered list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.show();
    }
}
