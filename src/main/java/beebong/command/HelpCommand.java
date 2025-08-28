package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

/**
 * Represents a Command that displays the list of available chatbot commands.
 */
public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        ui.showCommands();
    }
}
