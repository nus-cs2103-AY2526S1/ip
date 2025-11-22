package watermelon.command;

import watermelon.TaskList;
import watermelon.Ui;
import watermelon.exception.StorageOperationException;
import watermelon.exception.WatermelonException;

/**
 * Represents a command that captures details entered by the user.
 * All subclasses of Command  must implement a {@link #execute()} method.
 */
public abstract class Command {
    protected static final String INDENT = " ".repeat(4);
    TaskList taskList;
    String message;
    Ui ui;

    /**
     * Executes the command by modifying the TaskList accordingly and saving the task where needed.
     * @throws StorageOperationException when a problem occurs during saving of task to data file.
     */
    public abstract void execute() throws StorageOperationException, WatermelonException;

    /**
     * Returns the message from Watermelon responding to user's command.
     */
    public abstract String getMessage();
}
