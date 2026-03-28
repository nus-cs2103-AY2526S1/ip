package peanutbutter.commands;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Represents a command to display the welcome messagee.
 */
public class WelcomeCommand extends Command {
    /**
     * Executes the WelcomeCommand by showing the welcome message via the UI.
     *
     * @param taskList The current TaskList
     * @param ui The UI object used to display messages
     * @return false since this command does not exit the application
     * @throws JuinException if any error occurs while interacting with the UI
     */
    @Override
    public boolean run(TaskList taskList, Ui ui) throws JuinException {
        ui.welcomeMessage();
        return false;
    }
}
