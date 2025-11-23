package stewie.command;

import stewie.exceptions.CommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        return taskList.listTask();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.LIST;
    }
}
