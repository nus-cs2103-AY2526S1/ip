package uxie.commands;

import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;

/**
 * Abstract class representing commands for Uxie to execute.
 *
 * @author junyan-k
 */
public abstract class Command {

    /**
     * Executes command.
     *
     * @param tasks current TaskList used by Uxie.
     * @param ui current Ui used by Uxie.
     * @param storage current Storage used by Uxie.*/
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /** Returns true if command is exit, else return false. */
    public abstract boolean isExit();

}
