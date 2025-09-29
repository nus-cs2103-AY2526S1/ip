package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

/**
 * A command that searches for a list of tasks in the TaskList whose
 * descriptions match a sub string.
 * This list of matching tasks is printed out.
 */
public class FindCommand extends Command {
    protected String subString;

    /**
     * Creates a new FindCommand instance with the given sub string to search for
     * 
     * @param subString Substring to search for in TaskList
     */
    public FindCommand(String subString) {
        this.subString = subString;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksString = tasks.findTasksToString(subString);
        if (tasksString.length() == 0) {
            return ui.printMessage("No matching tasks found!");
        } else {
            return ui.printMessage(tasksString);
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
