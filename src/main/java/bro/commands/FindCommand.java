package bro.commands;

import bro.tasks.Task;
import bro.tasks.Tasks;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        String output = "Here bro, these are the matching tasks in your list:";

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getEntry(i);
            if (task.getDescription().contains(keyword)) {
                output += String.format("\n%d. %s", i + 1, task);
            }
        }
        return output;
    }
}
