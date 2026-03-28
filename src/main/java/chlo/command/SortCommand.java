package chlo.command;


import chlo.storage.Storage;
import chlo.task.TaskList;
import chlo.ui.Ui;

/**
 * Sorts the tasks in terms of deadline or event start time. Todo tasks are pushed to the end of the list.
 */
public class SortCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort();
        setString(ui.getTaskList(tasks));
        storage.save(tasks);
    }
}
