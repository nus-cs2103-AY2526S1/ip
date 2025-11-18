package LeeKuanYew.Command;

import LeeKuanYew.Task.Task;
import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.TaskList;

public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     * If the task list is empty, returns a message indicating no tasks are present.
     * Otherwise, shows the list of tasks via the UI.
     *
     * @param taskList the list of current tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage handler
     * @return a message indicating the result of the command
     */
    @Override
    public String execute (TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            return ui.showMessage("""
                    This is not a game of cards!
                    Your list is empty, get to work!""");
        }

        StringBuilder sb = new StringBuilder("Your work awaits you!\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            char isDone = task.checkDone() ? 'X' : ' ';
            sb.append((i + 1) + ". " + task.toString() + "\n");
        }

        return ui.showMessage(sb.toString().trim());
    }
}
