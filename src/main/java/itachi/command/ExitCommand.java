package itachi.command;

import itachi.Storage;
import itachi.Ui;
import itachi.task.TaskList;

/**
 * A command that exits the application.
 * <p>
 * When executed, it prints a farewell message to the user.
 * The {@link #isExit()} method returns true to signal termination.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a farewell message via the Ui.
     *
     * @param tasks TaskList (not used in this command)
     * @param ui Ui to display messages
     * @param storage Storage (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list should never be null";
        ui.showLine();
        ui.showMessage("byeee see u in the leaf village again!");
        ui.showLine();
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return true, since this command signals exit
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
