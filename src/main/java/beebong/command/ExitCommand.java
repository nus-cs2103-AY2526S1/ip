package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

/**
 * Represents a Command that tells the chatbot to terminate.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        // Try to save tasks to file before exiting
        taskList.writeTasksToFile(storage);
        ui.showMessage("Bing Bing! Tasks saved successfully!");
        ui.showExitMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
