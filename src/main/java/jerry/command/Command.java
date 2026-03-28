package jerry.command;

import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * An abstract class that represents a command in Jerry application.
 * This class serves as the base for all specific commands that can be executed within the program.
 */
public abstract class Command {
    /**
     * Stores the response message generated after executing the command.
     */
    protected String response;

    /**
     * Executes this command using the provided task list, user interface, and storage.
     *
     * @param tasks   the task list to operate on.
     * @param ui      the user interface for input/output.
     * @param storage the storage system to save or load tasks.
     * @throws JerryException to handle if invalid operations or data issues occur.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException;

    /**
     * This method determines whether the application should stop executing which returns true
     * or continue based on the command which will return false.
     *
     * @return true if the command ends the program, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * This method returns a string representation of this command, typically used for display.
     *
     * @return a string description of this command.
     */
    public abstract String getString();
}
