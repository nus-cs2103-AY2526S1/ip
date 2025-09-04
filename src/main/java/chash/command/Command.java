package chash.command;

import chash.exception.ChashException;
import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashUi;

public abstract class Command {
    public abstract void execute(TaskList tasks, ChashUi ui, ChashDb db) throws ChashException;
    public boolean isExit() {
        //Default implementation is false
        return false;
    }
}
