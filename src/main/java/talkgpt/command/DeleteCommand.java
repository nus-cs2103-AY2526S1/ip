package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.task.Task;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents a command to delete a task from the task list in the TalkGPT application.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand to delete a task at the specified index.
     *
     * @param index Index of the task in the list to be deleted (1-based, will be converted to 0-based).
     */
    public DeleteCommand(String index) {
        this.index = Integer.parseInt(index) - 1;
    }

    /**
     * Executes the delete task function for the TaskList, UI, and Storage.
     *
     * @param list TaskList from which the task will be deleted.
     * @param ui UI for displaying the deleted task message.
     * @param storage Storage for updating the persistent task data.
     * @return The confirmation message after deleting the task.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        Task task = list.deleteTask(index);
        storage.delete(index);

        return ui.deleteTask(task, list.size());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) obj;
        return index == that.index;
    }
}
