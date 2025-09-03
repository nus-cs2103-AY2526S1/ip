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
    public String execute(TaskList taskList, Storage storage) throws BBongException {
        String res = "BeeBong signing off! Bing back soon for more chats!\n\n";
        // Try to save tasks to file before exiting
        try {
            taskList.writeTasksToFile(storage);
            res = "Bing Bing! Tasks saved successfully!\n\n" + res;
        } catch (BBongException e) {
            res = "Bong Alert! - Existing Tasks were not saved!\n\n" + res;
        }
        return res + "Chat closing in 3 seconds...";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
