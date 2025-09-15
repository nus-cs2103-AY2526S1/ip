
package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;

/**
 * Represents a command that lists all tasks in the task list.
 * When executed, ListCommand retrieves all tasks currently
 * stored in TaskList tasks and displays them in a formatted string.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     *
     * Initialises the command type as LIST.
     */
    public ListCommand() {
        super(CommandHandle.CommandType.LIST);
    }

    /**
     * Executes the list command by returning a string representation of all tasks.
     *
     * @param tasks   the current list of tasks
     * @param storage the storage handler (not used in this command)
     * @return a formatted string of all tasks
     * @throws RafayelException if an error occurs while retrieving the task list
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        return tasks.getTaskList();
    }

}
