package mel.commands;

import mel.apps.Parser;
import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.TaskList;

/**
 * Represents the mark command
 */
public class MarkCommand extends Command {
    private String argument;

    /**
     * Constructor for mark command
     *
     * @param argument
     */
    public MarkCommand(String argument) {
        this.argument = argument;

    }

    /**
     * Prints out the output when a task is marked
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws MelException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        ui.printOut(tasks.mark(Parser.handleIndex(argument)));

    }

}
