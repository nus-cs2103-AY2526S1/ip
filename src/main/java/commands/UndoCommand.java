package commands;

import java.util.Stack;

import exception.RainyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents an undo operation that reverses the last executed command.
 */
public class UndoCommand extends Command {
    private final Stack<Command> commandHistory;

    public UndoCommand(Stack<Command> commandHistory) {
        this.commandHistory = commandHistory;
    }

    @Override
    public void execute(Object... args) throws RainyException {
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];

        if (commandHistory.isEmpty()) {
            message = "hmm... nothing to undo!";
            return;
        }

        Command lastCommand = commandHistory.pop();
        lastCommand.undo(tasks, ui, storage);
        message = lastCommand.getMessage();
    }
}
