package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.model.TaskList;

/**
 * A {@link Command} that lists all tasks currently stored in the {@link TaskList}.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Delegate to {@link TaskList#list()} to generate the formatted list of tasks.</li>
 *   <li>Wrap the resulting message into a {@link CommandResult} for output.</li>
 *   <li>If no tasks exist, returns the friendly message provided by {@link TaskList#list()}.</li>
 * </ul>
 *
 * @since 1.0
 */
public final class ListCommand implements Command {

    /**
     * Executes the list command on the given {@link TaskList}.
     * <p>
     * Retrieves all tasks in the list and formats them into a user-facing string.
     * If there are no tasks, returns a message indicating that the list is empty.
     *
     * @param tasks the task list to operate on
     * @return a {@link CommandResult} containing the list of tasks or an empty-list message
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        String msg = tasks.list().toString();
        return CommandResult.of(msg);
    }
}
