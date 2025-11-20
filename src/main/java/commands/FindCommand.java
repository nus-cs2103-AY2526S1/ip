package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import ui.Ui;
/**
 * The FindCommand class represents a command to find tasks in the task list that match a given keyword.
 * It now accepts the raw argument string and validates inside execute().
 */
public class FindCommand extends Command {
    private final String rawArg;

    public FindCommand(String rawArg) {
        this.rawArg = rawArg;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (rawArg == null || rawArg.trim().isEmpty()) {
            throw new JackException("Keyword must not be empty");
        }
        var foundTasks = taskList.find(rawArg.trim());
        return ui.showFind(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false; // This command doesn't cause the program to exit
    }
}
