package jimbot.command;

import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Represents a command to display the list of available commands.
 * Returns a formatted command list from the UI.
 *
 * @author limjimin-nus
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command by returning a list of all available commands.
     *
     * @param userList Current task list of the user (not used in this command).
     * @param userStorage Storage handler for persisting tasks (not used in this command).
     * @param user UI component for formatting responses.
     * @return A string containing the list of available commands.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) {
        return user.commandList();
    }
}
