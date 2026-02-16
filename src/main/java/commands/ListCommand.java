package commands;

import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    public ListCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the list command to display all tasks.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        Ui.showAllTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
