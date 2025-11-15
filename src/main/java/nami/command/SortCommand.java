package nami.command;

import nami.exception.DukeException;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.ui.Ui;

public class SortCommand extends Command {
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.sort();
        storage.save(task.asList());
        return "Tasks have been sorted";
    }
}
