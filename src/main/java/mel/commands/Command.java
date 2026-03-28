package mel.commands;

import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.TaskList;

/**
 * Represents the commands for the bot
 *
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MelException;

    /**
     * Returns false for every command except bye so it does not exit the program
     *
     * @return
     */
    public boolean isExit() {
        return false;

    }

}
