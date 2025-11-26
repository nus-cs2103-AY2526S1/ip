package aries.command;

import aries.task.TaskList;
import aries.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks, Ui ui) {
        return new CommandResult(ui.showTaskList(tasks), false, false);
    }

}
