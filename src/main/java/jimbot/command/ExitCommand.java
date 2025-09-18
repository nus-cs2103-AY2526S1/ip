package jimbot.command;

import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Represents a command that terminates the program.
 *
 * @author limjimin-nus
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command.
     * The command returns a farewell message without changing anything.
     *
     * @param userList (unused in this command).
     * @param userStorage Storage manager (unused in this command).
     * @param user UI manager used to generate the farewell message.
     * @return Farewell message from the UI.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) {
        return user.goodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
