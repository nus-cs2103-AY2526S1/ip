package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws BBongException;

    public boolean isExit() {
        return false;
    }
}
