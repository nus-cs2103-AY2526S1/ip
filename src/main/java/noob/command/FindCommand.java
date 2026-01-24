package noob.command;

import noob.storage.Storage;
import noob.task.TaskList;
import noob.ui.Ui;

public class FindCommand extends Command {
    String matcher;

    public FindCommand(String matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList filteredTasks = tasks.filterTasks(this.matcher);
        String msg = filteredTasks.getNumTasks() == 0
                ? String.format("No task matching \"%s\"", this.matcher)
                : String.format("Here are the matching tasks in your list:\n%s", filteredTasks);
        return ui.displayMessage(msg);
    }
}
