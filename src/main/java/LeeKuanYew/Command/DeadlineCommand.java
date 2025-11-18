package LeeKuanYew.Command;

import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.DeadlineTask;
import LeeKuanYew.Task.TaskList;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private String description;
    private String deadline; // let DateTime conversion and any exception throwing be done by LeeKuanYew.Task.DeadlineTask class

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the deadline command by creating a new DeadlineTask and adding it to the task list.
     * Displays a confirmation message via the UI. If the deadline format is invalid,
     * returns an error message.
     *
     * @param taskList the list of current tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage handler (not used in this command)
     * @return a message indicating the result of the command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            DeadlineTask task = new DeadlineTask(description, deadline);
            taskList.add(task);
            return ui.showMessage(
                    "I shall add:\n" +
                            "  " + task + "\n" +
                            "Singapore needs you to complete your " + taskList.size() + " tasks"
            );
        } catch (DateTimeParseException e) {
            return ui.showMessage("Date must be in yyyy-mm-dd format.");
        }
    }
}
