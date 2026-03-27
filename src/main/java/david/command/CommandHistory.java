package david.command;

import java.util.Stack;

import david.exception.DavidException;
import david.storage.Storage;
import david.task.TaskList;

public class CommandHistory {
    private Stack<Command> history;

    public CommandHistory() {
        this.history = new Stack<>();
    }

    public void add(Command c) {
        if (c.isUndoable()) {
            history.push(c);
        }
    }

    public void undo(TaskList tasks, Storage storage) throws DavidException {
        if (history.isEmpty()) {
            throw new DavidException("Eh got nothing to undo lah");
        }
        Command top = history.pop();
        top.undo(tasks, storage);
    }
}
