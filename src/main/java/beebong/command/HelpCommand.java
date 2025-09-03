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
    public String execute(TaskList taskList, Storage storage) throws BBongException {
        return UI.commands;
    }
}
