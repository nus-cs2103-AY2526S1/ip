package jimbot.command;

import java.util.List;

import jimbot.exception.EmptyListException;
import jimbot.exception.JimbotException;
import jimbot.exception.NoSuchTaskException;
import jimbot.storage.Storage;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Represents a command that searches for tasks containing a given keyword.
 * <p>
 * This command looks through the task list for any task descriptions
 * that match the specified search term, ignoring case sensitivity.
 * The matching tasks are then returned in a formatted list.
 * </p>
 *
 * Example usage:
 * <pre>
 * find book
 * </pre>
 * This would list all tasks whose description contains the word "book".
 *
 * @author limjimin-nus
 */
public class FindCommand implements Command {
    private final String userInput;

    /**
     * Constructs a {@code FindCommand} with the given user input.
     *
     * @param input The raw user input string.
     */
    public FindCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the find command.
     *
     * @param userList The task list to search through.
     * @param userStorage The storage manager (unused in this command).
     * @param user The UI manager used to format and display results.
     * @return A formatted string of all matching tasks.
     * @throws NoSuchTaskException If there is no task in the list that matches the description.
     * @throws EmptyListException If the list is empty.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        if (userList.getTaskList().isEmpty()) {
            throw new EmptyListException("find");
        }

        String description = userInput.toLowerCase()
                .substring(4)
                .trim();
        List<Task> tasks = userList.findTasks(description).getTaskList();

        return user.printList(tasks);
    }
}
