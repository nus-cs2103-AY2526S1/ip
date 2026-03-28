package chlo.command;

import chlo.storage.Storage;
import chlo.task.TaskList;
import chlo.ui.Ui;

/**
 * Represents a list command that displays the current list of tasks.
 * Initiated by the input "list".
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setString(ui.getTaskList(tasks));
    }
}
