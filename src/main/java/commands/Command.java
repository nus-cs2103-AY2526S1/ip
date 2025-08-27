package commands;

import exception.RainyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException;
    public boolean isExit() {
        return false;
    }
}
