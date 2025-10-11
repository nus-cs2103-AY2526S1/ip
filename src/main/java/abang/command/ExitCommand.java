package abang.command;

import abang.task.TaskList;
import abang.ui.UI;
import abang.storage.Storage;
import abang.exception.AbangException;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by printing a goodbye message
     * and terminating the program.
     *
     * @param taskList the current task list
     * @param ui       the UI object for interaction
     * @param storage  the storage object for saving tasks
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws AbangException {
        return "Thank you and have a good day!";
    }

    public boolean isExit() {
        return true;
    }
}
