package tsundere.command;

import tsundere.log.Log;
import tsundere.storage.AbstractStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * Handles the execution of displaying a list of tasks to the user with the specified search parameter.
 */
public class FindCommand extends AbstractCommand {
    private final String name;

    /**
     * Searches for the tasks that includes the specified name.
     * @param name Substring to search for
     */
    public FindCommand(String name) {
        super(false, false);
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, AbstractStorage storage, Log log) {
        TaskList filteredList = tasks.find(name);
        ui.displayMessage(UiMessages.LIST_TASKS, filteredList);
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, AbstractStorage storage) {
        assert false : "Not undoable";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand command) {
            return this.name.equals(command.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return "Find Command: " + name;
    }
}
