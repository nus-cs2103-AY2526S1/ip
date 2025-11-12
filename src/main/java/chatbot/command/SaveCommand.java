package chatbot.command;

import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command that saves the current list of tasks to persistent storage.
 * <p>
 * After execution, all tasks in the TaskList are saved to the file system
 * and a confirmation message is displayed to the user.
 */
public class SaveCommand extends Command {

    /**
     * Executes the save command by writing all tasks in the TaskList to storage.
     * Displays a confirmation message to the user via the UI.
     *
     * @param tasks   the TaskList containing the tasks to be saved
     * @param ui      the Ui to show messages to the user
     * @param storage the Storage to save the tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        storage.saveTasks(tasks); // save all tasks to file
        return "Tasks have been successfully saved!";
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because SaveCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

