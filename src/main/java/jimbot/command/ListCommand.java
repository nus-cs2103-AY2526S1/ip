package jimbot.command;

import jimbot.exception.EmptyListException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Represents a command to display all tasks in the user's task list.
 *
 * @author limjimin-nus
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by returning a formatted string of all tasks.
     *
     * @param userList Current task list of the user.
     * @param userStorage Storage handler for persisting tasks (not used in this command).
     * @param user UI component for formatting responses.
     * @return A string representing all tasks in the user's task list.
     * @throws EmptyListException If userList is empty.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws EmptyListException {
        if (userList.getTaskList().isEmpty()) {
            throw new EmptyListException("do");
        }
        return user.printList(userList.getTaskList());
    }
}