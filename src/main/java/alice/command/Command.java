package alice.command;

import alice.Storage;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws AliceException;

    public boolean isExit() {
        return false;
    }
}
