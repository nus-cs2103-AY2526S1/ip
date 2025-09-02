
package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;

/**
 * List user command that lists all tasks stored.
 */
public class ListCommand extends Command {

    /**
     * Constructs a list command.
     *
     * @param commandType stores LIST command type.
     */
    public ListCommand(Parser.CommandType commandType) {
        super(Parser.CommandType.LIST);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        return tasks.getTaskList();
    }

}
