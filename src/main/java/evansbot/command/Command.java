package evansbot.command;

import evansbot.Exceptions.EvansBotException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;
/**
 * Represents a command that is to be done on TaskList.
 */
public abstract class Command {
    /**
     * Executes the command.
     * If anything is invalid, throws an EvansBotException.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user (not used in this command).
     * @param storage Storage used to save the updated task list.
     * @throws EvansBotException If anything is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException;

    public boolean isExit() {
        return false;
    }
}
