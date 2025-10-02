package bobby.command;

import bobby.bobbyexception.BobbyException;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Command to delete a specific task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param taskIndex Index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command, removing the specified task and saving changes.
     *
     * @param tasks The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws BobbyException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BobbyException(
                "Whatdatmean thats not a task number fam choose a number from 1 to "
                + tasks.size()
            );
        }
        Task removed = tasks.delete(taskIndex);
        StringBuilder response = new StringBuilder();
        response.append("Noted. I've removed this task:").append(System.lineSeparator())
                .append(removed.toString()).append(System.lineSeparator())
                .append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        try {
            storage.saveTasks(tasks.getAll());
        } catch (Exception e) {
            response.append(System.lineSeparator())
                    .append("Failed to save tasks: ").append(e.getMessage());
        }
        return response.toString();
    }
}
