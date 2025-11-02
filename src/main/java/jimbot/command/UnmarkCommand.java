package jimbot.command;

import jimbot.exception.EmptyListException;
import jimbot.exception.JimbotException;
import jimbot.exception.NoSuchTaskException;
import jimbot.storage.Storage;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

/**
 * Represents a command to unmark a task as not done in the task list.
 * The command identifies the task to unmark based on the user input.
 *
 * @author limjimin-nus
 */
public class UnmarkCommand implements Command {
    private final String userInput;

    /**
     * Constructs an UnmarkCommand with the specified user input.
     *
     * @param input Raw user input specifying which task to unmark.
     */
    public UnmarkCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the unmark operation on the specified task list.
     * Marks the task at the given index as not done, updates the storage,
     * and returns a formatted response message for the user.
     *
     * @param userList The task list on which the unmark operation is performed.
     * @param userStorage The storage instance used to persist changes to tasks.
     * @param user The UI instance used to generate the response message.
     * @return A string message confirming the task has been unmarked.
     * @throws NoSuchTaskException If the specified task index does not exist.
     * @throws EmptyListException If the userList is empty.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        if (userList.getTaskList().isEmpty()) {
            throw new EmptyListException("unmark");
        }

        int index = Parser.parseIndex(userInput, "unmark", userList.getTaskCount());

        Task task = userList.getTask(index);
        task.markAsUndone();
        userStorage.update(userList);
        return user.unmarkRes(userList, index);
    }
}
