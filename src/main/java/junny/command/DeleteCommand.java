package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index the index of the task to delete (1-based)
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the given index,
     * updating the UI, and saving to storage.
     * If the index is invalid, prints an error message.
     *
     * @param tasks   the current list of tasks
     * @param ui      the UI handler to display messages
     * @param storage the storage handler to save/load tasks
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            ui.deleteTask(tasks.get(index - 1), tasks.size() - 1);
            tasks.remove(tasks.get(index - 1));
            storage.saveAllTasks(tasks);

        } catch (NumberFormatException e) {
            // handle exception 3
            ui.printError("Please enter a valid number for delete.");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            // handle exception 3
            ui.printError("The task number you give does not exist. Please check again!");
        }
    }
}
