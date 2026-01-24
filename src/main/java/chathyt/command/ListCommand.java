package chathyt.command;

/**
 *  Represents a command to list all tasks.
 */

import java.util.ArrayList;
import chathyt.task.Task;
import chathyt.storage.Storage;
import chathyt.ui.Ui;

public class ListCommand implements Command {

    /**
     *
     * @param tasks The list of existing tasks.
     * @param storage The storage handler for saving tasks.
     * @param ui The UI handler for displaying messages.
     * @return A string message confirming all tasks listed.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) {
        return Ui.showList(tasks); // display all tasks
    }

    @Override
    public boolean isExit() {
        return false; // list command does not exit the program
    }
}