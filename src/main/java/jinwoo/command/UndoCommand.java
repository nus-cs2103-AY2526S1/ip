package jinwoo.command;

import jinwoo.exception.DukeException;
import jinwoo.storage.Storage;
import jinwoo.tasks.TaskList;
import jinwoo.util.Ui;

/**
 * Undo command.
 */
public class UndoCommand extends Command {
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.undoState();
        storage.saveTasks(tasks.getTasks());
        ui.printUndo(tasks);
    }
}
