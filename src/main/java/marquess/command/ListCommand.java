package marquess.command;

import marquess.Storage;
import marquess.TaskList;

/**
 * Command to list existing tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return taskList.getTaskDisplay();
    }
}
