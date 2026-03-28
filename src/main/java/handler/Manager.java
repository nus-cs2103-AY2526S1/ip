package handler;

import hachi.TaskList;

/**
 * Represents a handler for executing a specific command on a {@link TaskList}.
 *
 * <p>Each implementation of this interface corresponds to a different user
 * command (e.g., add, delete, mark, find). The {@code execute} method applies
 * the command logic to the provided {@link TaskList} and returns a string
 * describing the outcome (typically for display to the user).</p>
 */
public interface Manager {
    String execute(TaskList tasks);
}
