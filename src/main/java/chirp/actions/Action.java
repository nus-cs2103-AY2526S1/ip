package chirp.actions;

import chirp.exceptions.ChirpException;
import chirp.io.Ui;
import chirp.tasks.TaskList;

/**
 * Represents base abstract object of all actions
 */
public abstract class Action {

    /**
     * Executes the action
     *
     * @param taskList List of tasks
     * @param ui       UI interface
     * @throws ChirpException
     */
    public abstract void execute(TaskList taskList, Ui ui) throws ChirpException;

    /**
     * Returns whether the action is a terminating action
     *
     * @return True if action terminates the chat
     */
    public boolean isExit() {
        return false;
    }
}
