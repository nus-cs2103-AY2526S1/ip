package command;

import sunday.Storage;
import sunday.TaskList;
import sunday.Ui;

/**
 * Command to list out all task.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayList(taskList);
    }
}
