package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

/**
 * Command implementation for listing all tasks in the task list.
 * Displays all current tasks to the user through the UI.
 */
public class ListCommand extends Command {
    
    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks the task list containing tasks to display
     * @param ui the user interface for displaying the task list
     * @param storage the storage component (not used in this command)
     * @throws LyraException never thrown by this command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        ui.showTaskList(tasks.getTasks());
    }
}
