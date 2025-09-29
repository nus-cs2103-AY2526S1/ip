package floydai.command;

import java.io.IOException;

import floydai.FloydException;
import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.UI;

/**
 * Represents a command that can be executed by FloydAI.
 * All specific commands should extend this class and implement the {@link #execute} method.
 */
public abstract class Command {

    /**
     * Executes the command, potentially modifying the task list, interacting with the UI,
     * and saving/loading data from storage.
     *
     * @param tasks   the TaskList to operate on
     * @param ui      the UI for interacting with the user
     * @param storage the Storage for persisting data
     * @throws FloydException if an error occurs during command execution
     * @throws IOException      if an I/O error occurs when interacting with the storage
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws FloydException, IOException;

    /**
     * Indicates whether this command is an exit command.
     *
     * @return true if this command exits the application, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
