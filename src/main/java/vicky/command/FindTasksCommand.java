package vicky.command;

import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to find tasks that contain the keyword.
 */
public class FindTasksCommand extends Command {

    private String str;

    public FindTasksCommand(String str) {
        this.str = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.matchingTasks(this.str.toLowerCase());
        return ui.showFindTasks(matchingTasks);
    }

}
