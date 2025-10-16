package lebron.command;

import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to exit the application.
 * When executed, it saves all tasks to storage and displays a goodbye message.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        for (Task task : taskList.getTasks()) {
            storage.writeTaskToFile(task);
        }
        return Constants.EXIT_MESSAGE;
    }
}
