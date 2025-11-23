package jinwoo.command;

import jinwoo.exception.DukeException;
import jinwoo.storage.Storage;
import jinwoo.tasks.TaskList;
import jinwoo.util.Ui;

/**
 * Abstract Command class to represent user commands.
 */
public abstract class Command {

    private String[] fullCommand;

    public Command(String[] fullCommand) {
        this.fullCommand = fullCommand;
    }

    protected Command() {
    }

    public String[] getCommand() {
        return fullCommand;
    }

    /**
     * Saves the current state before executing the command
     * Got idea of separating execute and executeCommand from copilot
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Exclude commands that do not modify the task list
        if (!(this instanceof UndoCommand || this instanceof ListCommand || this instanceof FindCommand
                || this instanceof ExitCommand)) {
            tasks.saveState();
        }
        executeCommand(tasks, ui, storage);
    }

    /**
     * Abstract method ovrridden by subclasses to execute specific commands.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
