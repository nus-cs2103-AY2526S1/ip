package bobby.command;

import bobby.bobbyexception.BobbyException;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand for the specified task index.
     *
     * @param taskIndex Index of the task to unmark as done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command, marking the specified task as not done and saving changes.
     *
     * @param tasks The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws BobbyException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BobbyException("What that mean that's not a task number fam choose a number from 1 to " + tasks.size());
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        StringBuilder response = new StringBuilder();
        response.append("OK! I've unmarked this task:").append(System.lineSeparator())
                .append(task);
        try {
            storage.saveTasks(tasks.getAll());
        } catch (java.io.IOException e) {
            response.append(System.lineSeparator())
                    .append("Failed to save tasks: ").append(e.getMessage());
        }
        return response.toString();
    }
}
