package command;

import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to exit the application.
 * Displays an exit message when executed.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new ByeCommand with BYE command type.
     */
    public ByeCommand() {
        super(CommandType.BYE);
    }

    /**
     * {@inheritDoc}
     * Displays the exit message to the user.
     *
     * @param taskList the task list (not used in this command)
     */
    @Override
    public String execute(TaskList taskList) {
        return UI.showExit();
    }
}
