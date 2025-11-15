package nami.command;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.exception.DukeException;

/**
 * Abstraction class for the entire Commands
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}

