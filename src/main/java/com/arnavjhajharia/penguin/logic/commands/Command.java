package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.PenguinException;
import com.arnavjhajharia.penguin.model.TaskList;

/**
 * Represents an executable command in the Penguin application.
 * <p>
 * Each concrete implementation of {@code Command} encapsulates a user action,
 * such as adding a task, listing tasks, marking/unmarking, deleting, or exiting.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Provide an {@link #execute(TaskList)} method to perform the command on a {@link TaskList}.</li>
 *   <li>Indicate whether the command signals program termination via {@link #isExit()}.</li>
 * </ul>
 *
 * @since 1.0
 */
@FunctionalInterface
public interface Command {

    /**
     * Executes the command against the given {@link TaskList}.
     *
     * @param tasks the task list to operate on
     * @return a {@link CommandResult} containing the outcome message and metadata
     * @throws PenguinException if execution fails (e.g., invalid arguments, unknown index)
     */
    CommandResult execute(TaskList tasks) throws PenguinException;

    /**
     * Indicates whether this command signals that the application should exit.
     * <p>
     * By default, this returns {@code false}. Exit commands (like {@code bye})
     * override this to return {@code true}.
     *
     * @return {@code true} if this is an exit command, otherwise {@code false}
     */
    default boolean isExit() { return false; }
}
