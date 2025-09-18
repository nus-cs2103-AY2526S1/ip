package charsiew.command;

import charsiew.storage.Storage;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents a command to undo the most recent user command, if undo is supported.
 * <p>
 * The {@code UndoCommand} retrieves the last executed command from the command history
 * and calls its {@code undo()} method, provided the command supports undoing.
 * </p>
 */
public class UndoCommand extends Command {
    private final Command lastCommand;

    public UndoCommand(Command lastCommand) {
        this.lastCommand = lastCommand;
    }

    /**
     * Executes the undo command by reverting the most recent command from history.
     *
     * @param tasks   The {@link TaskList} containing all tasks.
     * @param ui      The {@link Ui} handling user interaction and output.
     * @param storage The {@link Storage} used for saving and loading tasks.
     * @return A confirmation message if undo was successful,
     *         or an error message if no undoable command exists.
     * @throws Exception If an error occurs while undoing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (lastCommand == null) {
            ui.showNoLastCommand();
        } else if (lastCommand.canUndo() == false) {
            return ui.showNoUndo();
        }

        lastCommand.undo(tasks, ui, storage);
        return ui.showUndo();
    }
}
