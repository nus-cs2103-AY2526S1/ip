package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    /**
     * Constructs a FindCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the find command, displaying tasks that contain the specified keyword.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the task list is empty, the command format is invalid, or no
     *         tasks match the keyword.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String[] parts = fullCommand.split(" ", 2);

        if (tasks.size() == 0) {
            throw new TinkertonException(
                    "I feel like your list is empty so there is no list to show...");
        } else if (parts.length < 2) {
            throw new TinkertonException("You seem to be missing some information...");
        } else if (parts[1].trim().isEmpty()) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        String keyword = parts[1].trim();

        TaskList filtered = tasks.filter(t -> t.name().contains(keyword));

        if (filtered.size() == 0) {
            throw new TinkertonException("No tasks found with that keyword!");
        }

        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < filtered.size(); i++) {
            result.append((i + 1)).append(". ").append(filtered.get(i)).append("\n");
        }

        return result.toString();
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as finding tasks does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
