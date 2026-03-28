package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.InvalidIndexException;
import com.arnavjhajharia.penguin.model.TaskList;

/**
 * A {@link Command} that marks a task in the {@link TaskList} as done or undone,
 * depending on the mode provided at construction.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Parse and validate the user-supplied index (1-based).</li>
 *   <li>Convert to zero-based indexing for internal operations.</li>
 *   <li>Call {@link TaskList#markDone(int)} or {@link TaskList#markUndone(int)} based on {@code markDone}.</li>
 *   <li>Throw {@link InvalidIndexException} if parsing fails or index is out of range.</li>
 * </ul>
 *
 * @since 1.0
 */
public final class MarkCommand implements Command {

    /**
     * Raw user input representing the task index to mark.
     * Preserved for error reporting.
     */
    private final String rawIndex;

    /**
     * Mode indicating whether to mark the task as done ({@code true}) or undone ({@code false}).
     */
    private final boolean markDone;

    /**
     * Constructs a new {@code MarkCommand} with the given raw index and mode.
     *
     * @param rawIndex the user-supplied task index (1-based)
     * @param markDone {@code true} to mark as done, {@code false} to mark as undone
     */
    public MarkCommand(String rawIndex, boolean markDone) {
        this.rawIndex = rawIndex;
        this.markDone = markDone;
    }

    /**
     * Executes the mark operation on the given {@link TaskList}.
     * <p>
     * Steps:
     * <ol>
     *   <li>Parse the raw index as an integer.</li>
     *   <li>Convert from user-facing 1-based index to zero-based index.</li>
     *   <li>Validate the index against the current task list size.</li>
     *   <li>Mark the task as done or undone depending on {@link #markDone}.</li>
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
            idx = Integer.parseInt(rawIndex) - 1; // convert 1-based user input to 0-based
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(rawIndex);
        }

        if (tasks.isInvalidIndex(idx)) throw new InvalidIndexException(idx);

        String msg = (markDone ? tasks.markDone(idx) : tasks.markUndone(idx)).toString();
        return CommandResult.of(msg);
    }
}
