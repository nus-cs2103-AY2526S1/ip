package uxie.commands;

import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;

/**
 * Command that exits Uxie program.
 *
 * @author junyan-k
 */
public class ExitCommand extends Command {

    /**
     * Generates an ExitCommand.
     */
    public ExitCommand() {

    }

    /**
     * {@inheritDoc}
     * Prints goodbye message and closes Scanner in Ui.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.appendGoodbye();
        ui.closeScanner();
    }

    /**
     * {@inheritDoc}
     * Returns true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
