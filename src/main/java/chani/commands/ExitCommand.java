package chani.commands;

import chani.Storage;
import chani.StorageException;
import chani.TaskList;
import chani.Ui;

/**
 * Represents Command to exit the Chani application.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand with a command keyword.
     * @param command The command keyword (e.g., "bye").
     * @param args Optional arguments (not used here).
     */
    public ExitCommand(String command, String... args) {
        super(command, args);
    }

    /**
     * Displays the goodbye message and saves tasks before exiting.
     * @return Goodbye message string.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String exitMessage;
        try {
            storage.save(taskList.getAllTasks());
            exitMessage = ui.showGoodbye();
        } catch (StorageException e) {
            exitMessage = e.getMessage() + " Clearing temporary storage...";
        }
        return exitMessage;
    }
}
