package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

/**
 * Represents a generic Command.
 */
public abstract class Command {
    /**
     * Executes the logic associated with this Command.
     *
     * @param taskList the task list to operate on.
     * @param ui the user interface for displaying messages.
     * @param storage the storage handler for saving tasks.
     * @throws BBongException if an error occurs during execution.
     */
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws BBongException;

    /**
     * Returns a boolean signifying whether this command should terminate the chatbot.
     *
     * @return {@code true} if the chatbot should exit after this command's execution, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
