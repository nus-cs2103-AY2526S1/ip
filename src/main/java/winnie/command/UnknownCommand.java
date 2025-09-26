package winnie.command;

import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("I don't know what that means :-( Try: list, todo, deadline, event, mark, unmark, delete, bye");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
