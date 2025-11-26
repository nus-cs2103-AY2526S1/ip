package aries.command;

import aries.AriesException;
import aries.task.TaskList;
import aries.ui.Ui;

/**
 * Represents a command that can be executed by the Aries application,
 * inheriting classes must implement the execute method.
 */
public interface Command {
    /**
     * Executes the command using the provided task list and user interface.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface for interaction.
     * @return A boolean indicating whether the task list is mutated.
     * @throws AriesException If an error occurs during command execution.
     */
    abstract CommandResult execute(TaskList tasks, Ui ui) throws AriesException;
}
