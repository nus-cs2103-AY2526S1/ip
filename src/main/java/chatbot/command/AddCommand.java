package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.Deadline;
import chatbot.task.Task;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;



/**
 * Represents a command to add a task to the TaskList.
 * When executed, it adds the task, shows a confirmation message to the user,
 * and updates the total number of tasks in the list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task the task to be added to the TaskList
     */
    public AddCommand(Task task) {
        assert task != null : "Task cannot be null";
        this.task = task;
    }

    /**
     * Executes the AddCommand:
     * - Adds the task to the provided TaskList
     * - Uses Ui to show confirmation messages
     * - Optionally can save to storage (if implemented)
     *
     * @param tasks   the TaskList to add the task to
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance for persistence
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        tasks.addTask(task);
        String taskStr;
        if (task instanceof Deadline) {
            taskStr = ((Deadline) task).toStringDisplay();
        } else {
            taskStr = task.toString();
        }
        assert taskStr != null : "Task description cannot be null";
        return "Got it. I've added this task:" + "\n"
                + taskStr + '\n'
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because AddCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

