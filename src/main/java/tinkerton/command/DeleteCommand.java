package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.Task;
import tinkerton.storage.Save;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a DeleteCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the delete command, removing the specified task from the list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command format is invalid or the task index is out of
     *         bounds.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new TinkertonException("Delete what...");
        }

        int taskId = Integer.parseInt(parts[1]) - 1;

        if (taskId < 0 || taskId > tasks.size() - 1) {
            throw new TinkertonException("Your numbering for your tasks may be abit off...");
        }

        Task removed = tasks.remove(taskId);

        save.save(tasks);

        return "Noted, I've removed this task:\n" + removed.toString() + "<SPLIT>Now you have "
                + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as deleting a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
