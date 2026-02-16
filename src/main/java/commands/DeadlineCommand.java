package commands;

import core.Deadline;
import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to add a Deadline task to the TaskList.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the command to add a Deadline task.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        String errorMessageForInvalidInputDeadlineTask = "I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE DEADLINE TASK!";
        if (parts.length == 1) {
            throw new TronaldDumpException(
                    errorMessageForInvalidInputDeadlineTask);
        }

        taskList.addTask(new Deadline(input.substring(9), false));
        Ui.showAddTask(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
