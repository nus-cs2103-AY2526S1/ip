package commands;

import core.Task;
import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the delete command to remove a task from the TaskList.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        String errorMessageForDeleteTaskNoTaskNumber = "Please specify the task number to delete.";
        if (parts.length != 2) {
            throw new TronaldDumpException(errorMessageForDeleteTaskNoTaskNumber);
        }

        String errorMessageForDeleteTaskInvalidTaskNumber = "Invalid task number.";
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= taskList.getSize()) {
            throw new TronaldDumpException(errorMessageForDeleteTaskInvalidTaskNumber);
        }
        
        Task removedTask = taskList.removeTask(taskNumber);
        Ui.showDeleteTask(taskList, removedTask, taskNumber);
        
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
