package kleebot.command;

import kleebot.ui.KleeExceptions;
import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;


/**
 * Represents an abstract command in the task manager application.
 * Each command encapsulates a specific user instruction
 * and can be executed with access to the tasklist, Ui, and storage variables.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The {@link TaskList} containing all tasks.
     * @param ui      The {@link Ui} responsible for displaying messages.
     * @param storage The {@link Storage} responsible for saving/loading tasks.
     * @throws KleeExceptions If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions;

    /**
     * Executes the command with the given task list, UI, and storage specifically for the GUI app.
     *
     * @param tasks   The {@link TaskList} containing all tasks.
     * @param ui      The {@link Ui} responsible for displaying messages.
     * @param storage The {@link Storage} responsible for saving/loading tasks.
     * @throws KleeExceptions If an error occurs while executing the command.
     */
    public abstract String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions;

    /**
     * Returns whether this command should terminate the program.
     *
     * @return {@code true} if this command signals program exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}


