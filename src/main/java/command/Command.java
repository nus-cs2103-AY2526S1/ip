package command;
import application.Storage;
import application.TaskList;
import application.Ui;

/**
 * Abstract base class for all command implementations.
 * Defines the interface for command execution and provides command type enumeration.
 */
public abstract class Command {
    /**
     * Enumeration of all supported command types.
     */
    public enum CommandType {
        LIST, EVENT, TODO, DEADLINE, MARK, UNMARK, DELETE, FIND, BYE;
    }

    /**
     * Executes the command with the given task list, UI, and storage components.
     * Each concrete command implementation defines its specific behavior.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface for displaying results.
     * @param storage The storage component for persisting changes.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    
    /**
     * Determines if this command should terminate the application.
     *
     * @return true if the command should exit the application, false otherwise.
     */
    public abstract boolean isBye();
}
