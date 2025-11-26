package aries.command;

import aries.AriesException;
import aries.task.TaskList;
import aries.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui) throws AriesException {
        if (keyword.isEmpty()) {
            throw new AriesException("OOPS!!! The keyword for find cannot be empty.");
        }

        TaskList foundTasks = new TaskList(tasks.findTaskByKeyword(keyword));

        return new CommandResult(ui.showFoundTasks(foundTasks), false, false);
    }
}
