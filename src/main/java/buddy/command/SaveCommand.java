package buddy.command;

import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

public final class SaveCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.toList());
        ui.showMessage("Saved.");
    }
}
