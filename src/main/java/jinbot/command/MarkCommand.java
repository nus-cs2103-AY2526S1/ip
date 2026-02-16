package jinbot.command;

import jinbot.JinBotException;
import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.ui.Ui;

/**
 * Represents a command to mark a task in the task list as done.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the given index string.
     * The string is parsed into a zero-based integer index.
     *
     * @param indexString The 1-based index of the task to mark as done, as a string.
     */
    public MarkCommand(String indexString) throws JinBotException {
        try {
            this.index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new JinBotException(
                    "Error! Number must be a valid integer.");
        }
    }

    /**
     * Executes the mark command by marking the specified task as done
     * in the task list and displaying a confirmation message.
     *
     * @param ui       The UI instance that handles user interaction.
     * @param taskList The task list containing the task to be marked as done.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task taskToMark = taskList.getTask(index);
        assert taskToMark != null : "taskToMark should not be null";
        taskToMark.markAsDone();

        String response = "Nice! I've marked this task as done:\n  " + taskToMark;
        ui.printBox(response);
        return response;
    }
}
