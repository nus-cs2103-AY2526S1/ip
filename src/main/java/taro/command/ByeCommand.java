package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaskList;
import taro.ui.Ui;

/**
 * Represents the {@code bye} command which ends the program.
 * When executed, this command will display a farewell message
 * through the {@link Ui} and signal the application to terminate.
 */
public class ByeCommand implements Command {

    /**
     * Executes the {@code bye} command.
     *
     * @param tasks    the current list of tasks; not used in this command
     * @param ui       the user interface used to show messages
     * @param storage  the storage handler; not used in this command
     * @return {@code true} to indicate that the program should exit
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        return true;
    }
}
