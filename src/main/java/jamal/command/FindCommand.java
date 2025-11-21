package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;

/**
 * Find Command Subclass of Command
 * Finds
 *
 */
public class FindCommand extends Command {

    protected String match;

    public FindCommand(String match) {
        this.match = match;
    }

    /**
     * Executes Command on TaskList
     * Finds tasks with description containing match
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.find(this.match);
    }
}
