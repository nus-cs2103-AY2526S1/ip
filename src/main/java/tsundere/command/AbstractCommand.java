package tsundere.command;

import tsundere.log.Log;
import tsundere.storage.AbstractStorage;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

/**
 *  The abstract command class that handles the execution of code for {@link TaskList},
 *  {@link AbstractUi}, and {@link AbstractStorage}
 */
public abstract class AbstractCommand {
    private final boolean isExit;
    private final boolean isUndoable;

    AbstractCommand(boolean isExit, boolean isUndoable) {
        this.isExit = isExit;
        this.isUndoable = isUndoable;
    }

    /**
     * Determines if the command should cause the program to close.
     *
     * @return true if program will exit after execution.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Determines if this command should be stored in the undo logs.
     * @return true if this command can be undone.
     */
    public boolean isUndoable() {
        return isUndoable;
    }

    /**
     * Runs the command with the corresponding implementation for handling {@link TaskList},
     * {@link AbstractUi}, and {@link TextStorage}
     *
     * @param tasks   The current list of tasks.
     * @param ui      Displays messages to the user.
     * @param storage Storage for the tasks.
     */
    public abstract void execute(TaskList tasks, AbstractUi ui, AbstractStorage storage, Log log);

    /**
     * Undoes the results of this command, bringing program back to a previous state.
     * @param tasks   The current list of tasks.
     * @param ui      Displays messages to the user.
     * @param storage Storage for the tasks.
     */
    public abstract void undo(TaskList tasks, AbstractUi ui, AbstractStorage storage);

    @Override
    public String toString() {
        return "Abstract Command";
    }
}

