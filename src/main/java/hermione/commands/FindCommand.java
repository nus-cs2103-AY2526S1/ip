package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.TaskList;

/**
 * FindCommand is a command that allows users to find task by searching for
 * a keyword in the task description.`
 */
public class FindCommand extends Command {

    public FindCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the find command to search for tasks containing the specified
     * keyword.
     *
     * @return A string listing all matching tasks.
     */
    @Override
    public String execute() {
        if (argument.isBlank()) {
            throw new TaskValidationException("Keyword cannot be empty");
        }

        String keyword = argument.trim();
        TaskList tasks = storage.getTasks();
        TaskList matchingTasks = tasks.getTasksByKeyword(keyword);

        return "I found these tasks matching your search:\n" + matchingTasks.toString()
                + "\nHope this helps you find what you're looking for!";
    }
}
