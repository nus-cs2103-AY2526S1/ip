package david.command;

import david.exception.DavidException;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * The superclass of all types of commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui User interface for displaying messages.
     * @param storage Save changes in the list.
     * @throws DavidException If execution of command goes wrong.
     */
    public abstract void execute(TaskList tasks, Ui ui,
                                    Storage storage) throws DavidException;

    /**
     * Checks whether the command will exit the program.
     *
     * @return boolean value, set as false by default.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command in GUI.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui User interface for displaying messages.
     * @param storage Save changes in the list.
     * @return The response of the command.
     * @throws DavidException If execution of command goes wrong.
     */
    public abstract String executeGui(TaskList tasks, Ui ui,
                                      Storage storage) throws DavidException;
}
