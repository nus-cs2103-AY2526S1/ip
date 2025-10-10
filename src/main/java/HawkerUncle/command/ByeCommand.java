package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

/**
 * Represents the "bye" command which exits the application.
 * When executed, it displays a farewell message and signals the program to exit.
 */
public class ByeCommand implements Command {
    /**
     * Executes the "bye" command by displaying a farewell message.
     * @param task The task list where the tasks are stored
     * @param ui The user interface object where messages are shown to the user.
     * @param storage The storage object where tasks are saved and loaded.
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return Ui.showBye();
    }

    /**
     * Checks if the command is an exit command.
     * @return true, since it is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
