package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.InvalidIndexException;
import com.arnavjhajharia.penguin.model.TaskList;

/**
 * A {@link Command} that deletes a task from the {@link TaskList}
 * at the specified index.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Parse and validate the raw user-supplied index (1-based).</li>
 *   <li>Convert to zero-based indexing for internal operations.</li>
 *   <li>Throw {@link InvalidIndexException} if parsing fails or index is out of bounds.</li>
 *   <li>Delegate actual removal and message formatting to {@link TaskList#delete(int)}.</li>
 * </ul>
 *
 * @since 1.0
 */
public final class DeleteCommand implements Command {

    /**
     * Raw user input representing the task index to delete.
     * Preserved for error reporting.
     */
    private final String rawIndex;

    /**
     * Creates a new {@code DeleteCommand} with the given raw index string.
     *
     * @param rawIndex the user-supplied index (expected to be 1-based)
     */
    public DeleteCommand(String rawIndex) {
        this.rawIndex = rawIndex;
    }

    /**
     * Executes the delete operation on the given {@link TaskList}.
     * <p>
     * Steps:
     * <ol>
     *   <li>Attempts to parse the raw index string as an integer.</li>
     *   <li>Adjusts from user-facing 1-based index to zero-based index.</li>
     *   <li>Validates that the index is within bounds.</li>
     *   <li>Delegates to {@link TaskList#delete(int)} for actual removal.</li>
     * </ol>
     *
     * @param tasks the task list to operate on
     * @return a {@link CommandResult} containing the success message
     * @throws InvalidIndexException if the index cannot be parsed or is out of range
     */
    @Override
    public CommandResult execute(TaskList tasks) throws InvalidIndexException {
        int idx;
        try {
            idx = Integer.parseInt(rawIndex) - 1; // user is 1-based
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(rawIndex);
        }
        if (tasks.isInvalidIndex(idx)) throw new InvalidIndexException(idx);

        // Let TaskList do the actual removal + message formatting
        String msg = tasks.delete(idx).toString();
        return CommandResult.of(msg);
    }
}
