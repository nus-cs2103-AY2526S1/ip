package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

/**
 * Represents a command to unmark a task as not completed.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructs an UnmarkCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public UnmarkCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the unmark command, marking the specified task as not completed.
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
            throw new TinkertonException("Unmark what...");
        }

        int unmarkId = Integer.parseInt(parts[1]) - 1;

        if (unmarkId < 0 || unmarkId > tasks.size() - 1) {
            throw new TinkertonException("Your numbering for your tasks may be abit off...");
        }

        tasks.get(unmarkId).uncomplete();

        StringBuilder result = new StringBuilder("OK, I've marked this task as not done yet:\n");
        result.append(tasks.get(unmarkId).toString()).append("\n");

        save.save(tasks);

        return result.toString();
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as unmark does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
