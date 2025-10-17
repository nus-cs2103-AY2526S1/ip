package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command that lists all tasks currently in the task list. It simply
 * delegates to {@link lux.ui.Ui#listTasks} for string formatting.
 */
public class ListCommand extends Command {
    /**
     * Return a formatted string containing all tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) {
        return ui.listTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
