package LeeKuanYew.Command;

import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.Task;
import LeeKuanYew.Task.TaskList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     * Displays a confirmation or error message via the UI depending on the validity of the index.
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
        taskList.remove(index);
        return ui.showMessage(
                "I've spent my whole lifetime building this. Sadly you won't. Removing:\n" +
                "  " + task + "\n" +
                "Singapore urges you to do your remaining " + taskList.size() + " tasks"
        );
    }
}
