package pepe.command;

import java.io.IOException;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Command to exit the application.
 * <p>
 * Extends the {@link Command} abstract class. When executed, it shows
 * a goodbye message via {@link Ui} and saves the current task list.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * <p>
     * Displays a goodbye message and saves the task list.
     *
     * @param tasks   the current task list
     * @param ui      the UI object to display messages
     * @param storage the storage object to save the task list
     * @throws PepeExceptions if there is an error saving the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            super.setResponse(ui.showUiSayBye());
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, always, as this command exits the program
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
