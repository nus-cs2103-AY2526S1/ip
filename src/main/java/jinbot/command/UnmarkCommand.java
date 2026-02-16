package jinbot.command;

import jinbot.JinBotException;
import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.ui.Ui;

/**
 * Represents a command to mark a task in the task list as not done.
 */
public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the given index string.
     * The string is parsed into a zero-based integer index.
     *
     * @param indexString The 1-based index of the task to unmark, as a string.
     */
    public UnmarkCommand(String indexString) throws JinBotException {
        try {
            this.index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new JinBotException(
                "Error! Number must be a valid integer.");
        }
    }

    /**
     * Executes the unmark command by marking the specified task as not done
     * in the task list and displaying a confirmation message.
     *
     * @param ui       The UI instance that handles user interaction.
     * @param taskList The task list containing the task to be unmarked.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task taskToUnmark = taskList.getTask(index);
        assert taskToUnmark != null : "taskToUnmark should not be null";
        taskToUnmark.markAsNotDone();

        String response = "OK, I've marked this task as not done yet:\n  "
            + taskToUnmark;
        ui.printBox(response);
        return response;
    }
}
