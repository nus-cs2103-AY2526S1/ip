package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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
        try {
            // VERY IMPORTANT: mark 2 extract 2, but it actually mark tasks[1]!!!
            // throw exception 3: may throw ArrayIndexOutOFBoundsException
            tasks.get(index - 1).markAsDone();
            ui.markDone(tasks.get(index - 1));
            storage.saveAllTasks(tasks);
        } catch (NumberFormatException e) {
            // handle exception 3
            ui.printError("Please enter a valid number for mark.");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            // handle exception 3
            ui.printError("The task number you give does not exist. Please check again!");
        }
    }
}
