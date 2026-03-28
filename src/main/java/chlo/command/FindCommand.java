package chlo.command;

import chlo.storage.Storage;
import chlo.task.TaskList;
import chlo.ui.Ui;

/**
 * Find command that handles find command
 */
public class FindCommand extends Command {
    protected String s;
    public FindCommand(String s) {
        this.s = s;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setString(ui.getFilteredList(tasks, s));
    }
}
