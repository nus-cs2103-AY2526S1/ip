package LeeKuanYew.Command;

import LeeKuanYew.Task.Task;
import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.TaskList;

public class MarkCommand extends Command {

    private int index;
    private boolean mark;

    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    /**
     * Executes the mark command by marking or unmarking the task at the specified index.
     * Returns a confirmation message via the UI. Handles invalid indices with error messages.
     *
     * @param taskList the list of current tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage handler
     * @return a message indicating the result of the command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (index < 0) {
            return ui.showMessage("No negative numbers! Don't be stupid.");
        }

        if (index >= taskList.size()) {
            return ui.showMessage("You overestimate your workload. Get to work!");
        }

        Task task = taskList.get(index);
        if (mark) {
            task.markDone();
            return ui.showMessage("You have the IRON in you! Good job!\n" + task);
        } else {
            task.unmarkDone();
            return ui.showMessage("No shame in failure. Pick yourself up, Singapore needs you.\n" + task);
        }
    }

}
