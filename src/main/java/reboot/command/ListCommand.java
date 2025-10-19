package reboot.command;

import reboot.RebootException;
import reboot.Storage;
import reboot.TaskList;
import reboot.gui.Gui;

/**
 * Represents a command that displays the current tasklist if not empty.
 */
public class ListCommand extends Command {

    /**
     * Checks if tasklist is empty.
     * If empty, output message to inform user that list is empty.
     * If not empty, display the current tasklist.
     *
     * @param tasks Tasklist to be displayed.
     * @param gui Gui outputs message.
     * @param storage Storage writes lines to file.
     */
    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws RebootException {
        if (tasks.isEmpty()) {
            return gui.showMessage("List function is useless without any tasks");
        } else {
            tasks.updateRecurringTasks();
            return gui.showTaskList(tasks);
        }
    }
}
