package tsundere.log;

import java.util.EmptyStackException;
import java.util.Stack;

import tsundere.command.AbstractCommand;
import tsundere.storage.AbstractStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

/**
 * Keeps track of the history of executed commands.
 */
public class Log {
    private final TaskList tasks;
    private final AbstractUi ui;
    private final AbstractStorage storage;
    private final Stack<AbstractCommand> stack = new Stack<>();

    /**
     * Initializes a log with the respective task list, ui, and storage.
     * @param tasks List of the current tasks
     * @param ui Ui to display messages to the user
     * @param storage Storage location of the tasks
     */
    public Log(TaskList tasks, AbstractUi ui, AbstractStorage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Adds the executed command to the log.
     * @param command The command to be stored in logs.
     */
    public void add(AbstractCommand command) {
        stack.add(command);
    }

    /**
     * Undoes the most recently executed command.
     * @throws TsundereNothingToUndoException Thrown if there are no more commands to undo.
     */
    public void undo() throws TsundereNothingToUndoException {
        try {
            AbstractCommand command = stack.pop();
            command.undo(tasks, ui, storage);
        } catch (EmptyStackException e) {
            throw new TsundereNothingToUndoException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Log log2) {
            return this.stack.equals(log2.stack);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return stack.hashCode();
    }
}
