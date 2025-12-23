package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the keyword.
     *
     * @param manager The TaskManager containing all tasks.
     * @return the output text for the GUI.
     * @throws GeorgeException If there's an error during execution.
     */
    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new GeorgeException("Please provide a keyword to search for.");
        }

        // Now findTasks returns a String directly
        return manager.findTasks(keyword);
    }

    @Override
    public String getCommandWord() {
        return "find";
    }
}
