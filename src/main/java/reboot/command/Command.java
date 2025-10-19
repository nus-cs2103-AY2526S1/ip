package reboot.command;

import reboot.RebootException;
import reboot.Storage;
import reboot.TaskList;
import reboot.gui.Gui;

/**
 * Represents a generic command that will be parsed by Parser.
 * Provides common properties and behaviors for all commands.
 */
public abstract class Command {

    /**
     * Executes the given command.
     */
    public abstract String execute(TaskList tasks, Gui ui, Storage storage) throws RebootException;

    /**
     * Returns true only for ExitCommand.
     * Returns false for every other command.
     */
    public boolean isExit() {
        return false; // default is not exit
    }
}
