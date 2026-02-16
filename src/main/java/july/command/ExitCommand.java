package july.command;

import java.util.ArrayList;

import july.data.Storage;
import july.tasks.Task;
import july.ui.Ui;

/**
 * Command to exit the application gracefully.
 * This command handles the termination process by saving all tasks
 * to storage and displaying a farewell message to the user.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command to terminate the application gracefully.
     * Saves all current tasks to storage and displays a goodbye message
     * through the user interface.
     *
     * @param tasks the ArrayList of tasks to save before exiting
     * @param ui the user interface component for displaying the goodbye message
     * @param storage the storage component for persisting task data
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        addResponses(ui.bye());
    }

    /**
     * Indicates whether the command represents a termination request.
     * This method returns true to signal that the application should exit
     * after this command is executed.
     *
     * @return true, indicating that the application should terminate
     */
    @Override
    public boolean isDone() {
        return true;
    }
}
