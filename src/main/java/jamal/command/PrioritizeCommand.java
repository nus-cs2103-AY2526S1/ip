package jamal.command;

import jamal.exception.InvalidCommandException;
import jamal.task.TaskList;
import jamal.util.Storage;

/**
 * Prioritize Command SubClass for prioritizing tasks
 */
public class PrioritizeCommand extends Command {

    protected int idx;
    protected int priority;

    /**
     * Constructor for Command
     * @param idx line to change
     * @param priority number to set
     */
    public PrioritizeCommand(int idx, int priority) {
        this.idx = idx;
        this.priority = priority;
    }

    /**
     * Executes Command on TaskList
     * Rewrite data at idx line in Storage to specified priority
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     * @throws InvalidCommandException Exception thrown if index is out of range of tasklist
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws InvalidCommandException {
        String result = taskList.prioritize(this.idx, this.priority);
        storage.prioritizeLine(this.idx, this.priority);
        return result;
    }
}
