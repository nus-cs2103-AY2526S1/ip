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
     * @param storage the storage handler for saving tasks.
     * @return a String result for the user.
     * @throws BBongException if an error occurs during execution.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws BBongException;

    /**
     * Returns a boolean signifying whether this command should terminate the chatbot.
     *
     * @return {@code true} if the chatbot should exit after this command's execution, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
