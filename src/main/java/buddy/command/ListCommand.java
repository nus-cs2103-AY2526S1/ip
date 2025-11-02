package buddy.command;

import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

public final class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.listAsString());
    }
}
