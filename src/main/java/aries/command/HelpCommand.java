package aries.command;

import aries.AriesException;
import aries.task.TaskList;
import aries.ui.Ui;

/**
 * Represents a command to display help information about available commands.
 */
public class HelpCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks, Ui ui) throws AriesException {
        return new CommandResult(ui.showAvailableCommands(), false, false);
    }
}
