package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

/**
 * An abstract class used to refer to a command that can be executed to make
 * changes to Johnny
 */
public abstract class Command {
    /**
     * Executes the command, editing the tasklist, printing messages, or saving to
     * storage
     * 
     * @param tasks   The task list of Johnny
     * @param ui      The ui class for printing messages and errors
     * @param storage The storage instance to save tasks
     * 
     * @return A string message to be displayed in the GUI
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if the command is a ByeCommand
     * 
     * @return boolean whether the command is a bye command
     */
    public abstract boolean isBye();
}
