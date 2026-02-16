package commands;

import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to unmark a task as not completed in the TaskList.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the unmark command to unmark a task as not completed in the TaskList.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        String errorMessageForUnmarkTaskNoTaskNumber = "Please specify the task number to unmark.";
        if (parts.length != 2) {
            throw new TronaldDumpException(errorMessageForUnmarkTaskNoTaskNumber);
        }

        int taskNumber = Integer.parseInt(parts[1]) - 1;
        String errorMessageForUnmarkTaskInvalidTaskNumber = "Invalid task number.";
        if (taskNumber < 0 || taskNumber >= taskList.getSize()) {
            throw new TronaldDumpException(errorMessageForUnmarkTaskInvalidTaskNumber);
        }
        taskList.unmarkTask(taskNumber);
        Ui.showUnmarkTask(taskList, taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
