package mel.commands;

import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.TaskList;

/**
 * Represents the list command
 */
public class ListCommand extends Command {

    /**
     * Returns the ListCommand object.
     * Throws an exception when extra argument is given.
     *
     * @param argument
     * @throws MelException.ExtraArgumentException
     */
    public ListCommand(String argument) throws MelException.ExtraArgumentException {
        if (argument != "") {
            throw new MelException.ExtraArgumentException("list");

        }
    }

    /**
     * Prints out the list of tasks
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printOut(tasks.toString());

    }

}
