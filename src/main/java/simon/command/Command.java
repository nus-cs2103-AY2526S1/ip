package simon.command;

import simon.storage.Storage;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents an abstract command that can be executed by the Simon chatbot.
 * A <code>Command</code> object encapsulates the logic for a specific user action.
 */
public abstract class Command {
    private String string;
    
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving tasks.
     * @throws Exception If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Sets the string result of the command execution.
     *
     * @param s The string to set.
     */
    public void setString(String s) {
        this.string = s;
    }

    /**
     * Gets the string result of the command execution.
     *
     * @return The result string.
     */
    public String getString() {
        return string;
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return True if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}