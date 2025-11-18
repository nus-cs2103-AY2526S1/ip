package LeeKuanYew.Command;

import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Task.ToDoTask;


public class ToDoCommand extends Command {

    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by creating a new ToDoTask and adding it to the task list.
     * Displays a confirmation message via the UI with the updated number of tasks.
     *
     * @param taskList the list of current tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage handler
     * @return a message indicating the result of the command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ToDoTask task = new ToDoTask(description);
        taskList.add(task);
        return ui.showMessage(
                "I shall add:\n" +
                "  " + task + "\n" +
                "Singapore needs you to complete your " + taskList.size() + " tasks"
        );
    }
}
