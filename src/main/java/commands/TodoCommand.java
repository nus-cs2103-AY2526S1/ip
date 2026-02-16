package commands;

import core.TaskList;
import core.Todo;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to add a Todo task to the TaskList.
 */
public class TodoCommand extends Command {

    public TodoCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the command to add a Todo task.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        String errorMessageForInvalidInputTodoTask = "I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE TODO TASK!";
        if (parts.length == 1) {
            throw new TronaldDumpException(errorMessageForInvalidInputTodoTask);
        }

        taskList.addTask(new Todo(input.substring(5), false));
        Ui.showAddTask(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
