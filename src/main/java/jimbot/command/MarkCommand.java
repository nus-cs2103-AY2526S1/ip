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
 * Represents a command to mark a specific task as done in the task list.
 *
 * @author limjimin-nus
 */
public class MarkCommand implements Command {
    private final String userInput;

    /**
     * Constructs a MarkCommand with the given user input.
     *
     * @param input Raw user input containing the command and task index.
     */
    public MarkCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param userList The current list of tasks.
     * @param userStorage The storage handler for persisting task changes.
     * @param user The UI component used to generate formatted responses.
     * @return A string confirming the task has been marked as done.
     * @throws NoSuchTaskException If the task index is invalid or does not exist.
     * @throws EmptyListException If the userlist is empty.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        if (userList.getTaskList().isEmpty()) {
            throw new EmptyListException("mark");
        }

        int index = Parser.parseIndex(userInput, "mark", userList.getTaskCount());

        Task task = userList.getTask(index);
        task.markAsDone();
        userStorage.update(userList);
        return user.markRes(userList, index);
    }
}
