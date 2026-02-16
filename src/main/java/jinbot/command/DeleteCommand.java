package jinbot.command;

import jinbot.JinBotException;
import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * The task to delete is specified by its index.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the given index string.
     * The string is parsed into a zero-based integer index.
     *
     * @param indexString The 1-based index of the task to delete, as a string.
     * @throws JinBotException If the index string is not a valid integer.
     */
    public DeleteCommand(String indexString) throws JinBotException {
        try {
            this.index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new JinBotException(
                    "Error! Number must be a valid integer.");
        }
    }

    /**
     * Executes the delete command by removing the task at the specified index
     * from the task list, and displaying a confirmation message through the UI.
     *
     * @param ui       The UI instance that handles user interaction.
     * @param taskList The task list from which the task is removed.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        int beforeSize = taskList.getSize();
        Task removed = taskList.removeTask(index);
        assert taskList.getSize() == beforeSize - 1
            : "Size of tasklist should be decreased by 1";

        String response = "Noted. I've removed this task:\n  " + removed
            + "\nNow you have " + taskList.getSize()
            + " tasks in the list.";

        ui.printBox(response);
        return response;
    }
}
