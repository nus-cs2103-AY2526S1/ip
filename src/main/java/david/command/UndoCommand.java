package david.command;

import david.exception.DavidException;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * Undo a command that modifies the task list, one at a time.
 */
public class UndoCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        boolean success = tasks.undo();
        if (success) {
            storage.save(tasks);
            ui.showMessage("Okay, I've undone the last action.");
        } else {
            ui.showMessage("There is nothing to undo!");
        }
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        boolean success = tasks.undo();
        if (success) {
            storage.save(tasks);
            return "Okay, I've undone the last action.";
        } else {
            return "There is nothing to undo!";
        }
    }
}
