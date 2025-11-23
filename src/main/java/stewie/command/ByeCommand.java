package stewie.command;

import stewie.exceptions.CommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {

    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        return Ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.BYE;
    }
}
