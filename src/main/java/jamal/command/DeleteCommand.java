package jamal.command;

import jamal.exception.InvalidCommandException;
import jamal.task.TaskList;
import jamal.util.Storage;

/**
 * Delete Command SubClass for deleting tasks
 */
public class DeleteCommand extends Command {

    protected int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes Command on TaskList
     * Deletes line at idx line in datafile and rewrites the whole Storage file
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     * @throws InvalidCommandException Exception thrown if index is out of range of tasklist
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws InvalidCommandException {
        String result = taskList.delete(this.idx);
        storage.deleteLine(this.idx);
        return result;
    }
}
