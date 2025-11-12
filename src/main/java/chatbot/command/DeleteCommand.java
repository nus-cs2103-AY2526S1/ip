package chatbot.command;

import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;


/**
 * Represents a command to delete a task from the TaskList.
 * When executed, it removes the task at the specified index.
 */
public class DeleteCommand extends Command {

    private int taskIndex; // The index of the task to delete

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex the index of the task to delete (0-based)
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DeleteCommand:
     * - Deletes the task at the given index from the TaskList
     * - Can be extended to show a confirmation message via Ui
     *
     * @param tasks   the TaskList to delete the task from
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance for persistence
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        return tasks.deleteTask(taskIndex);
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because DeleteCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
