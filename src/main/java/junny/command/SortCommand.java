package junny.command;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

import java.util.ArrayList;

public class SortCommand extends Command {
    /**
     * Executes the list-on-date command by showing tasks scheduled
     * on the specified date.
     *
     * @param tasks   List of current tasks.
     * @param ui      The UI used to interact with the user.
     * @param storage The storage handler (not used here).
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printSorted(tasks);
    }
}
