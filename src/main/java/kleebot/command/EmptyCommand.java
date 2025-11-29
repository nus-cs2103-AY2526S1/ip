package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

public class EmptyCommand extends Command{

    /**
     * Returns nothing.
     *
     * @param tasks   The {@link TaskList} containing all tasks.
     * @param ui      The {@link Ui} responsible for displaying messages.
     * @param storage The {@link Storage} responsible for saving/loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        return "";
    }
}
