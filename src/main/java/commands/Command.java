package commands;

import exceptions.DawaeException;
import storage.Storage;
import taskstuff.TaskList;
import ui.Ui;

public abstract class Command {
    /**
     * Executes this command, potentially mutating the task list and producing output via the UI.
     * Implementations may persist changes using the storage component.
     *
     * @param taskList the task list to operate on.
     * @param ui the UI to display messages to the user.
     * @param storage the storage component to save or load data.
     * @return a String representing the result or message of the command execution.
     * @throws DawaeException if command execution fails due to invalid input or I/O errors.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DawaeException;
    
    /**
     * Returns whether the application should exit after this command.
     *
     * @return {@code true} if the command signals termination; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
