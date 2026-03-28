package mel.commands;

import mel.apps.Parser;
import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.TaskList;

/**
 * Represents the unmark command
 */
public class UnmarkCommand extends Command {
    private String argument;

    /**
     * Constructor for unmark command
     *
     * @param argument
     */
    public UnmarkCommand(String argument) {
        this.argument = argument;

    }

    /**
     * Prints out the output when a task is unmarked
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws MelException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        ui.printOut(tasks.unmark(Parser.handleIndex(argument)));


    }

}
