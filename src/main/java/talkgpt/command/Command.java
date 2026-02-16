package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents an abstract command in the TalkGPT application.
 * Subclasses implement specific command behaviors for task management.
 */
public abstract class Command {

    /**
     * Constructs a Command with the specified exit status.
     *
     * @param isExit True if this command should exit the application, false otherwise.
     */
    public Command() {}

    /**
     * Executes the command with the given TaskList, UI, and Storage.
     *
     * @param list TaskList to operate on.
     * @param ui UI for user interaction.
     * @param storage Storage for persistent data.
     * @return The result of the command execution as a string.
     */
    public abstract String execute(TaskList list, Ui ui, Storage storage);
}
