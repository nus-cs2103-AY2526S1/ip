package buddy.command;


import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

public final class ByeCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.toList());
        ui.showGoodbye();
    }

    @Override public boolean isExit() {
        return true;
    }
}

