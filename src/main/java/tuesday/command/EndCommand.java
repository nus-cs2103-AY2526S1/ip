package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.TaskList;
import tuesday.ui.Ui;


/**
 * Represent a command to terminate the program
 * Save all tasks to storage before exiting and
 * shows exit messages to the user
 */
public class EndCommand extends Command {
    private final String ERROR_MESSAGE = "ERROR: ";
    /**
     *  Execute the exit command by saving the current tasks to storage
     *  and displaying exit messages to the user
     *  If saving fails, an error message is shown
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showDataSaving();
        try {
            storage.saveData(tasks.getTasks());
            ui.showDataSaved();
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }

       ui.showExit();
    }

    /**
     * Return the response for End command
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveData(tasks.getTasks());
            ui.showDataSaved();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            return ERROR_MESSAGE + e.getMessage();

        }
        ui.showExit();
        return "";
    }

    /**
     * Indicate whether this command should exit the program.
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
