package LeeKuanYew.Command;

import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.EventTask;
import LeeKuanYew.Task.TaskList;

public class EventCommand extends Command {

    private String description;
    private String startDate;
    private String endDate;

    public EventCommand(String description, String startDate, String endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Executes the event command by creating a new EventTask and adding it to the task list.
     * Displays a confirmation message via the UI with the updated number of tasks.
     *
     * @param taskList the list of current tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage handler
     * @return a message indicating the result of the command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        EventTask task = new EventTask(description, startDate, endDate);
        taskList.add(task);
        return ui.showMessage(
                "I shall add:\n" +
                "  " + task + "\n" +
                "Singapore needs you to complete your " + taskList.size() + " tasks"
        );
    }
}
