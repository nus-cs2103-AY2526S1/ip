package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;


/**
 * A command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks currently in the task list. Tasks are numbered from 1.
     *
     * @param tasks   The task list containing tasks.
     * @param ui      The UI for displaying messages.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are a list of things you've made!!");
        tasks.readList();
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        return ui.formatList(tasks);
    }
}
