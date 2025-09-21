package merlie.command;

import merlie.exception.MerlieException;
import merlie.listfile.ListFile;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Abstract class representing a generic command. All commands should extend
 * this class and implement the execute method.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks Task list to update or query.
     * @param ui UI to display messages.
     * @param listFile ListFile for persistent storage in a file.
     * @throws MerlieException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, ListFile listFile) throws MerlieException;

    /**
     * Returns whether this command signals the program to exit. Always false for
     * non-exit commands.
     */
    public boolean isExited() {
        return false;
    }
}
