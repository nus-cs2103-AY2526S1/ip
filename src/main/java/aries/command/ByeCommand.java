package aries.command;

import aries.task.TaskList;
import aries.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks, Ui ui) {
        return new CommandResult(ui.showExitMessage(), false, true);
    }
}
