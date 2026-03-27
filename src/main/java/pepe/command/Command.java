package pepe.command;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Abstract class representing a command in Pepe.
 * <p>
 * Each concrete command (e.g., TodoCommand, MarkCommand) must implement the execute method.
 * Commands can optionally signal the application to exit by overriding isExit().
 */
public abstract class Command {
    private String response;

    /**
     * Executes the command.
     *
     * @param tasks   the current TaskList
     * @param ui      the UI for displaying messages
     * @param storage the Storage for saving or loading tasks
     * @throws PepeExceptions if an error occurs during command execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions;

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return true if the application should exit after this command, false otherwise
     */
    public boolean isExit() {
        return false;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getString() {
        return this.response;
    }
}

