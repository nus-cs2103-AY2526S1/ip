package itachi.command;

import java.io.IOException;

import itachi.ItachiException;
import itachi.Storage;
import itachi.Ui;
import itachi.task.TaskList;

/**
 * Undo the most recent command executed by user
 */
public class UndoCommand extends Command {
    private final CommandHistory history = CommandHistory.getInstance();

    public UndoCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        if (this.history.isEmpty()) {
            ui.showMessage("Nothing to undo!");
            return;
        }
        Command lastCommand = this.history.pop();
        try {
            lastCommand.undo(tasks, ui, storage);
        } catch (UnsupportedOperationException e) {
            ui.showMessage("This command cannot be undone!");
        }
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
