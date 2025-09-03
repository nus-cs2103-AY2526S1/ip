package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

/**
 * Represents a null Command.
 */
public class NullCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws BBongException {
        return "Something went boom in B. Bong’s circuits.";
    }
}
