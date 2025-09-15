package rafayel.command;

import java.util.ArrayList;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;

/**
 * Find Command that uses task number with 
 */
public class FindCommand extends Command {
    private final String stringToFind;

    /**
     * Constructor for finding tasks.
     * 
     * @param taskNumber the string task number to find from the list of tasks.
     * @throws RafayelException 
     */
    public FindCommand(String stringToFind) throws RafayelException {
        super(CommandHandle.CommandType.FIND);
        this.stringToFind = stringToFind;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {

        ArrayList<Task> matchedTasks = tasks.matchTasks(stringToFind);
        assert matchedTasks != null : "Matched tasks list should not be null";

        String res = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matchedTasks.size(); i++) {
            res += i + 1 + "." + matchedTasks.get(i).toString() + "\n";
        }
        return res;
    }

}
