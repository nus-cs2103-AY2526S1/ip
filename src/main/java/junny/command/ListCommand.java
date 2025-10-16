package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

/**
 * Represents a command that lists all tasks currently stored.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks to the user.
     *
     * @param tasks   List of current tasks.
     * @param ui      The UI used to interact with the user.
     * @param storage The storage handler (not used here).
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printAllTasks(tasks, tasks.size());
    }
}
