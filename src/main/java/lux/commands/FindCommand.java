package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command that searches for tasks whose descriptions contain a given
 * substring. The search is case-sensitive and matches any task containing
 * the provided argument.
 */
public class FindCommand extends Command {
    private String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) {
        return ui.findTasks(tasks.find(arguments));
    }

    public boolean isExit() {
        return false;
    }
}
