package jinwoo.command;

import jinwoo.storage.Storage;
import jinwoo.tasks.TaskList;
import jinwoo.util.Ui;

/**
 * List command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in the task list.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
