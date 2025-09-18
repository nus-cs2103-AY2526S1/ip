package jimbot.command;

import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Represents a command that clears the task list of all tasks.
 *
 * @author limjimin-nus
 */
public class ClearCommand implements Command {

    /**
     * Executes the command by clearing the task list.
     * The tasks are removed from task list and the storage is updated.
     *
     * @param userList Task list to be cleared.
     * @param userStorage Storage manager to update after clearing.
     * @param user UI manager used to generate the response message.
     * @return Response message confirming deletion of the task.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) {
        userList.clearList();
        userStorage.update(userList);
        return user.clear();
    }
}
