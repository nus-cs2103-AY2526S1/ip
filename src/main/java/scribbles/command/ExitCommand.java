package scribbles.command;

import scribbles.Main;
import scribbles.Scribbles;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;
import scribbles.ui.Ui;

/**
 * Provides the command logic to exit Scribbles.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) {
        storage.saveFile(taskList);
        Main.exit();
        return Ui.getExitMsg();
    }
}
