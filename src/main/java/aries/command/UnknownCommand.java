package aries.command;

import aries.AriesException;
import aries.task.TaskList;
import aries.ui.Ui;

/**
 * Represents an unknown command.
 */
public class UnknownCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks, Ui ui) throws AriesException {
        throw new AriesException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
