package commands;

import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to sort all tasks.
 */
public class SortCommand extends Command {

    public SortCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the sort command to sort all tasks and display them.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        taskList.sortTasks();
        Ui.showSortedTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
