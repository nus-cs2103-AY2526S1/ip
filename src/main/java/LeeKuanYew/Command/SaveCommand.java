package LeeKuanYew.Command;

import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import java.io.IOException;

public class SaveCommand extends Command {

    /**
     * Executes the save command by attempting to write the current task list to storage.
     * Displays a confirmation message via the UI if successful, or an error message if an IOException occurs.
     *
     * @param taskList the list of current tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage handler
     * @return a message indicating the result of the command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.save(taskList);
            return ui.showMessage("It has been written down.");
        } catch (IOException e) {
            return ui.showMessage(e.getMessage());
        }
    }
}
