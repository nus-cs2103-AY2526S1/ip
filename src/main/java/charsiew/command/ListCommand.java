package charsiew.command;

import charsiew.storage.Storage;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents a command to display all tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks using the UI.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object used to display the tasks.
     * @param storage The Storage object (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }

    /**
     * @return false, a List Command cannot be undone.
     **/
    @Override
    public boolean canUndo() {
        return false;
    }
}
