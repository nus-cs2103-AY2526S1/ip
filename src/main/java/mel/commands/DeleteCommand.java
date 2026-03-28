package mel.commands;

import mel.apps.Parser;
import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.TaskList;

/**
 * Represents the delete command
 */
public class DeleteCommand extends Command {

    private String argument;

    /**
     * Constructor for delete command
     *
     * @param argument
     */
    public DeleteCommand(String argument) {
        this.argument = argument;

    }

    /**
     * Prints out the output when a task is removed
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws MelException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        ui.printOut(tasks.remove(Parser.handleIndex(argument)));

    }

}
