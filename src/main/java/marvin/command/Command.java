package marvin.command;

import marvin.task.TaskList;

/**
 * An abstract class representing a command that Marvin can execute.
 */
public abstract class Command {
    /**
     * Performs a given action based on the concrete class.
     *
     * @param taskList The task list in which the changes should be reflected in.
     * @return CommandResult object representing the reply of Marvin.
     */
    public abstract CommandResult execute(TaskList taskList);

    /**
     * Returns whether the program should exit after the command is executed.
     */
    public boolean isExit() {
        return false;
    }
}
