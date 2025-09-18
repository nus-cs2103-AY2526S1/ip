package chiikawa.command;

import chiikawa.ChiikawaException;
import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;

/**
 * Abstract class that all other Commands will inherit from.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the corresponding commands.
     *
     * @param tasks TaskList that holds all the current tasks.
     * @param ui Ui that is in charge of all the outputs to the screen.
     * @param storage Storage that is in charge of saving and loading save files.
     * @return String representation of what Chiikawa will say.
     * @throws ChiikawaException Exceptions unique to Chiikawa.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ChiikawaException;

    public boolean isExit() {
        return this.isExit;
    }
}
