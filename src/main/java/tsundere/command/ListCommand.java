package tsundere.command;

import tsundere.log.Log;
import tsundere.storage.AbstractStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * Command to list out all the tasks to the user.
 */
public class ListCommand extends AbstractCommand {
    public ListCommand() {
        super(false, false);
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, AbstractStorage storage, Log log) {
        ui.displayMessage(UiMessages.LIST_TASKS, tasks);
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, AbstractStorage storage) {
        assert false : "Not undoable";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }

    @Override
    public int hashCode() {
        return ListCommand.class.hashCode();
    }

    @Override
    public String toString() {
        return "List Command";
    }
}
