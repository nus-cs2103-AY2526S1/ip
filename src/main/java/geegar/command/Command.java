package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.TaskList;

/**
 * This class is the parent class that standardises the operations that a Command should have
 */
public abstract class Command {

    /**
     * Executes the command
     *
     * @param tasks
     * @param gui
     * @param storage
     * @throws GeegarException
     */
    public abstract void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException;

    /**
     * Checks if the command is an Exit type of command
     * This will determine if the while loop for chatbot should be ended or not
     *
     * @return
     */
    public boolean isExit() {
        return false;
    };
}



