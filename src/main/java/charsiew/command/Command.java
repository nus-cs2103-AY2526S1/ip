package charsiew.command;

import charsiew.storage.Storage;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents an abstract command in the CharSiew chatbot.
 * Each command encapsulates a user instruction and defines how it should
 * be executed, potentially interacting with the task list, storage, and UI.
 * Subclasses of {@code Command} must implement the {@link #execute(TaskList, Ui, Storage)}
 * method to provide specific behavior.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The {@link TaskList} containing all current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance used to save or load tasks.
     * @return A {@link String} representing the response of this command,
     *         typically displayed to the user.
     * @throws Exception If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Determines whether this command signals the application to exit.
     * <p>
     * By default, this returns {@code false}. Commands that terminate
     * the application (e.g., an {@code ExitCommand}) should override this.
     * </p>
     *
     * @return {@code true} if the program should exit after this command,
     *         otherwise {@code false}.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Undo this command if possible (default: do nothing)
     *
     * @param tasks   The {@link TaskList} containing all current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance used to save or load tasks.
     * @throws Exception If an error occurs during command execution.
     */
    public void undo(TaskList tasks, Ui ui, Storage storage) throws Exception {
        // default implementation: do nothing
    }

    /**
     * @return true if the command can be undone, false otherwise.
     **/
    public boolean canUndo() {
        return false;
    }
}
