package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract String getResponse(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
