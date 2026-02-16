package commands;

import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to mark a task as completed in the TaskList.
 */
public class MarkCommand extends Command {

    public MarkCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the mark command to mark a task as completed in the TaskList.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        String errorMessageForMarkTaskNoTaskNumber = "Please specify the task number to mark.";
        if (parts.length != 2) {
            throw new TronaldDumpException(errorMessageForMarkTaskNoTaskNumber);
        }

        int taskNumber = Integer.parseInt(parts[1]) - 1;
        String errorMessageForMarkTaskInvalidTaskNumber = "Invalid task number.";
        
        if (taskNumber < 0 || taskNumber >= taskList.getSize()) {
            throw new TronaldDumpException(errorMessageForMarkTaskInvalidTaskNumber);
        }
       
        taskList.markTask(taskNumber);
        Ui.showMarkTask(taskList, taskNumber);
    
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
