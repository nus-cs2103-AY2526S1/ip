package mel.commands;

import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.TaskList;

/**
 * Represents the find command
 */
public class FindCommand extends Command {
    private String argument;

    /**
     * Constructor for find command
     *
     * @param argument
     */
    public FindCommand(String argument) {
        this.argument = argument;

    }

    /**
     * Finds the relevant tasks from the given argument and prints out the list of tasks
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws MelException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        ui.printOut(tasks.findTask(argument));

    }

}
