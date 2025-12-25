package khat.command;

import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.TaskList;
import khat.ui.Ui;

/** Represents a command to be executed in the chatbot. */
public abstract class Command {

    /**
     * Executes the command using the given task list, UI, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving/loading tasks.
     * @throws KhatException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KhatException;

    /**
     * Returns true if this command should exit the chatbot, false otherwise.
     *
     * @return True if the chatbot should end conversation, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
