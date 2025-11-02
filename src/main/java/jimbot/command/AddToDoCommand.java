package jimbot.command;

import jimbot.exception.InvalidToDoException;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.tasktype.ToDo;
import jimbot.ui.UI;

/**
 * Represents a command that adds a {@link ToDo} task to the task list.
 * The description of the task is extracted from the user input
 * following the {@code todo} keyword.
 *
 * Example input:
 * {@code todo read book}
 *
 * This would create a new ToDo task with the description
 * "read book" and add it to the task list.
 *
 * @author limjimin-nus
 */
public class AddToDoCommand implements Command {
    private final String userInput;

    /**
     * Constructs an AddToDoCommand with the specified user input.
     *
     * @param input The raw user string input.
     */
    public AddToDoCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the command by creating a new ToDo task and adding it
     * to the task list. Updates storage and generates a confirmation
     * message via the UI.
     *
     * @param userList Task list to which the new task will be added.
     * @param userStorage Storage manager to update after adding the task.
     * @param user UI manager used to generate the response message.
     * @return Response message confirming the addition of the task.
     * @throws InvalidToDoException If the description is empty.
     * @throws JimbotException If an error occurs while updating the storage
     *                         or adding the task.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        String description = userInput.substring(4).trim();

        if (description.isEmpty()) {
            throw new InvalidToDoException();
        } else {
            ToDo userToDo = new ToDo(description);
            userList.addToList(userToDo);
            userStorage.update(userList);

            return user.addTask(userToDo, userList.getTaskCount());
        }
    }
}
