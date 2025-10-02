package bobby.command;

import bobby.bobbyexception.BobbyException;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Command to mark a specific task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DoneCommand for the specified task index.
     *
     * @param taskIndex Index of the task to mark as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the done command, marking the specified task as done and saving changes.
     *
     * @param tasks The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws BobbyException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BobbyException("Whatdatmean thats not a task number fam choose a number from 1 to " + tasks.size());
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        StringBuilder response = new StringBuilder();
        response.append("Nice! I've marked this task as done:").append(System.lineSeparator())
                .append(task.toString());
        try {
            storage.saveTasks(tasks.getAll());
        } catch (Exception e) {
            response.append(System.lineSeparator())
                    .append("Failed to save tasks: ").append(e.getMessage());
        }
        return response.toString();
    }
}
