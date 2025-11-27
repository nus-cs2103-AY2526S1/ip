package whchenyicn.command;

import whchenyicn.task.TaskList;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

/**
 * A command to list all tasks in TaskList.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tlist, Ui ui, Storage storage) {
        assertUi (tlist, ui, storage);
        return ui.printList(tlist);
    }
}
