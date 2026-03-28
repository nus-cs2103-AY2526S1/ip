package mel.commands;

import mel.exceptions.MelException;

import mel.tasks.TaskList;

import mel.apps.Ui;
import mel.apps.Storage;

/**
 * Represents the bye command which exits the program
 *
 */
public class ByeCommand extends Command {

    /**
     * Returns the ByeCommand command
     *
     * @param argument
     * @throws MelException.ExtraArgumentException
     */
    public ByeCommand(String argument) throws MelException.ExtraArgumentException {
        if (argument != "") {
            throw new MelException.ExtraArgumentException("bye");

        }

    }

    /**
     * Executes nothing due to the nature of the ByeCommand
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printOut(" Bye! Hope to see you again soon!");

    }

    /**
     * Returns true to exit the program loop
     *
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return true;

    }


}
