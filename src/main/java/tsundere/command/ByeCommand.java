package tsundere.command;

import tsundere.log.Log;
import tsundere.storage.AbstractStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * Prints a goodbye message to the user and exits the program.
 */
public class ByeCommand extends AbstractCommand {
    public ByeCommand() {
        super(true, false);
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, AbstractStorage storage, Log log) {
        ui.displayMessage(UiMessages.EXIT);
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, AbstractStorage storage) {
        assert false : "Not undoable";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }

    @Override
    public int hashCode() {
        return ByeCommand.class.hashCode();
    }

    @Override
    public String toString() {
        return "Bye Command";
    }
}
