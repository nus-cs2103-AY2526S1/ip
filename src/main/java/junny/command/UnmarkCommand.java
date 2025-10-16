package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
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
            if (tasks.get(index - 1).isDone()) {
                tasks.get(index - 1).markAsNotDone();
                ui.markUndone(tasks.get(index - 1));
                storage.saveAllTasks(tasks);
            } else {
                ui.printError("The task is not done yet, and you do not need to undo it!");
            }
        } catch (NumberFormatException e) {
            // handle exception 3
            ui.printError("Please enter a valid number for unmark.");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            // handle exception 3
            ui.printError("The task number you give does not exist. Please check again!");
        }
    }
}
