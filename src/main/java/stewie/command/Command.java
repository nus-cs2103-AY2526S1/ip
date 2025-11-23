package stewie.command;

import stewie.exceptions.CommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;

/**
 * Represents a command that can be executed in the application.
 */
public interface Command {
    /**
     * Executes the command with the given task list and storage.
     *
     * @param taskList The task list to operate on.
     * @param storage The storage to save/load data.
     * @return Response message to display to the user.
     * @throws CommandException If command execution fails.
     */
    String execute(TaskList taskList, Storage storage) throws CommandException;

    /**
     * Indicates if this command should exit the program.
     * Most commands don't exit
     *
     * @return True if this command should exit the program, false otherwise.
     */
    default boolean isExit() {
        return false;
    }

    /**
     * Returns Command Type Enum
     *
     * @return Command Type
     */
    CommandType getCommandType();
}
