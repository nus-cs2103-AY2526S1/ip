package commands;

import ineffaexceptions.IneffaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Abstract class containing methods that user Command children need to have.
 */
public abstract class Command {
    private final boolean isExit;
    private final CommandsEnum commandType;

    /**
     * Instantiaties class
     *
     * @param isExit whether command causes program to exit
     * @param commandType Track type of command of child class
     */
    public Command(boolean isExit, CommandsEnum commandType) {
        this.isExit = isExit;
        this.commandType = commandType;
    }

    /**
     * Performs logic for different command types.
     *
     * @param tasks List of tasks.
     * @param ui Ui provides ability to display text on screen.
     * @param storage Storage provides file handling methods
     * @return Message from executing task
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IneffaException;

    public boolean getExit() {
        return this.isExit;
    }

    public CommandsEnum getCommandType() {
        return this.commandType;
    }
}
