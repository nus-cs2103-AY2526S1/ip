package reboot.command;

import reboot.RebootException;
import reboot.Storage;
import reboot.TaskList;
import reboot.gui.Gui;

/**
 * Represents a command that ends the conversation with the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Gives the user a bye message via the gui.
     *
     * @param tasks The current Tasklist.
     * @param gui Gui outputs message.
     * @param storage Storage writes to the file.
     */
    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws RebootException {
        return gui.showBye();
    }
}
