package commands;

import storage.Storage;
import taskstuff.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    /** {@inheritDoc} */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}