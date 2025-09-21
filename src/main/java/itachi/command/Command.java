package itachi.command;

import java.io.IOException;

import itachi.ItachiException;
import itachi.Storage;
import itachi.Ui;
import itachi.task.TaskList;

/**
 * An abstract command that can be executed by Itachi.
 * Each of the command (e.g., ToDoCommand, ExitCommand) will extend
 * this class and implement the {@link #execute(TaskList, Ui, Storage)} method.
 */
public abstract class Command {

    /**
     * Executes the command using the given task list, user interface and storage.
     * @param tasks the list that contains all current tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for reading and writing task data
     * @throws ItachiException if the command fails due to invalid input or state
     * @throws IOException if reading or writing to storage fails
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException;
    /**
     * Returns whether this command is an exit command.
     *
     * @return false by default, overridden
     *         by ExitCommand to return true
     */
    public boolean isExit() {
        return false;
    }

    public void undo(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        throw new UnsupportedOperationException("Undo operation is not supported for this command!");
    }

    public boolean isUndoable() {
        return true;
    }
}


