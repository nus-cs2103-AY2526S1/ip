package lenny.logic.command;

import lenny.logic.storage.Storage;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;

/**
 * Represents an abstract command that can be executed by the program.
 * Each command encapsulates a specific user action (e.g., add, delete, list),
 * and when executed, produces a response as a {@code String}.
 * <p>
 * Concrete subclasses should implement the {@link #execute(TaskList, Storage, Ui)}
 * method to define their behavior.
 */
public abstract class Command {

    /**
     * Executes the command using the given task list, storage, and UI.
     * Subclasses implement this method to perform specific operations
     * such as adding, deleting, or listing tasks.
     *
     * @param tasks   The {@code TaskList} containing all tasks.
     * @param storage The {@code Storage} responsible for persisting changes.
     * @param ui      The {@code Ui} component for user interactions.
     * @return A string response to be shown to the user.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui);
    /**
     * Indicates whether this command will terminate the program.
     * <p>
     * By default, this method returns {@code false}. Commands that
     * terminate the program (e.g., {@code ExitCommand}) should override
     * this method to return {@code true}.
     *
     * @return {@code true} if the program should exit after this command,
     *         {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
