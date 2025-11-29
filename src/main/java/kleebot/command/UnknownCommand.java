package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

/**
 * A command that handles unknown user input.
 */
public class UnknownCommand extends Command {

    /**
     * Responds with an error message that indicates the KleeBot does not understand
     * the user input.
     * <p>
     * @param tasks   Not used in this command.
     * @param ui      The UI to display error messages.
     * @param storage Not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.throwTantrum();
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        return ui.formatTantrum();
    }
}
