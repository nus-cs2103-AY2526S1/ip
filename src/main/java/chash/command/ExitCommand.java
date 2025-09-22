package chash.command;

import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashUi;

/** Command to terminate CHASH. */
public class ExitCommand extends Command {
    public ExitCommand() {}

    /**
     * {@inheritDoc}
     * Prints the exit message through {@code ChashUi}
     */
    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        ui.printMsg("Bye. Hope to see you again soon!");
    }

    /**
     * {@inheritDoc}
     * Returns {@code true} to signal to stop execution
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
