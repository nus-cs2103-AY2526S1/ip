package david.command;

import david.exception.DavidException;
import david.storage.Storage;
import david.task.TaskList;

public class UndoCommand extends Command {
    CommandHistory history;

    public UndoCommand(CommandHistory history) {
        this.history = history;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DavidException {
        this.history.undo(tasks, storage);
        return "Ok I undo the latest command liao";
    }
}
