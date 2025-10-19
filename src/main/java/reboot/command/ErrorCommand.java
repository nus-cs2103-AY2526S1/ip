package reboot.command;

import reboot.Storage;
import reboot.TaskList;
import reboot.gui.Gui;

/**
 * Represents a command that will deal with exceptions thrown by reboot
 * and feedback to user the error.
 */
public class ErrorCommand extends Command{
    private final String message;

    /**
     * Constructs a new ErrorCommand with the corresponding message.
     * @param message Message to output to user.
     */
    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) {
        return gui.showError(message);
    }
}
