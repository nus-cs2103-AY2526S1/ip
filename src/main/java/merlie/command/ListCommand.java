package merlie.command;

import merlie.listfile.ListFile;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Displays all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     * Displays all tasks in the list to the UI.
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) {
        if (list.isEmpty()) {
            ui.taskSizeOutput(list.size());
        } else {
            ui.showList(list);
        }
    }
}
