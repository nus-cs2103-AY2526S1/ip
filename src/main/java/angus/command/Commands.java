package angus.command;

import angus.exception.AngusException;

/**
 * Represents the abstract base class for all commands.
 * <p>
 * Each commands encapsulate an action that the command will execute when called, as well
 * as whether the command is an exit command which will break out of the program loop.
 */
public abstract class Commands {
    /**
     * Represents the enumeration of all available commands known by Angus.
     */
    public enum CommandList {
        bye,
        mark,
        unmark,
        list,
        todo,
        deadline,
        event,
        delete,
        find,
        sort
    }

    /**
     * Executes the given command when called.
     *
     * @return Result from executing the command.
     * @throws AngusException If command cannot be executed.
     */
    public abstract String execute() throws AngusException;

    /**
     * Defines whether a command is an exit command.
     * <p>
     * An exit command is defined as a command that will lead to closing the program.
     * @return Whether the command is an exit command.
     */
    public abstract boolean isExit();
}
