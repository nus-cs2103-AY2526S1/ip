package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Represents the {@code bye} command.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage<TaskList> storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
