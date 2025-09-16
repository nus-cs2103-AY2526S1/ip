package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the task list is empty.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        if (tasks.size() == 0) {
            throw new TinkertonException(
                    "I feel like your list is empty so there is no list to show...");
        }

        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }

        return result.toString();
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as listing tasks does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
