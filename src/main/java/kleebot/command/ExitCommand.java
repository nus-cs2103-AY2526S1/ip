package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

/**
 * A command that terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, signaling the program to terminate.
     *
     * @param tasks   Not used in this command.
     * @param ui      The Ui that handles exit messages.
     * @param storage Not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        return ui.formatExit();
    }


    /**
     * Returns {@code true}, indicating this command signals program termination.
     *
     * @return {@code true}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
