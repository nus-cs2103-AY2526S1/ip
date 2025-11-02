package jimbot.command;

import jimbot.exception.EmptyListException;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

/**
 * Represents a command that deletes a task from the task list.
 * The task index is extracted from the user input following
 * the {@code delete} keyword.
 *
 * Example input:
 * {@code delete 2}
 *
 * This would delete the second task in the task list.
 *
 * @author limjimin-nus
 */
public class DeleteCommand implements Command {
    private final String userInput;

    /**
     * Constructs a DeleteCommand with the specified user input.
     *
     * @param input The raw user string input.
     */
    public DeleteCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the command by deleting the task at the specified index.
     * The task is removed from the task list and the storage is updated.
     *
     * @param userList Task list from which the task will be deleted.
     * @param userStorage Storage manager to update after deletion.
     * @param user UI manager used to generate the response message.
     * @return Response message confirming deletion of the task.
     * @throws JimbotException If the task index is invalid or other
     *                         task-related errors occur.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        if (userList.getTaskList().isEmpty()) {
            throw new EmptyListException("delete");
        }

        int taskCount = userList.getTaskCount();
        int index = Parser.parseIndex(userInput, "delete", taskCount);

        Task task = userList.getTask(index);
        userList.deleteFromList(userList.getTask(index));
        userStorage.update(userList);

        return user.deleteTask(task, taskCount - 1);
    }
}
