package rafayel.command;

import java.util.ArrayList;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;

/**
 * Represents a command that searches for tasks containing a given keyword.
 * Returns all tasks whose description contains the specified substring.
 */
public class FindCommand extends Command {

    /** Stores the string to find in tasks. */
    private final String stringToFind;

    /**
     * Constructor for FindCommand.
     * 
     * @param taskNumber the string task number to find from the list of tasks.
     * @throws RafayelException if the input is invalid.
     */
    public FindCommand(String stringToFind) throws RafayelException {
        super(CommandHandle.CommandType.FIND);

        if (stringToFind == "") {
            throw new RafayelException("Please state what task you want to find •-•");
        }

        this.stringToFind = stringToFind;
    }

    /**
     * Executes the find command by returning a list of matching tasks.
     *
     * @param tasks the task list.
     * @param storage the storage handler.
     * @return a formatted string of all matching tasks.
     * @throws RafayelException if an error occurs during search.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {

        ArrayList<Task> matchedTasks = tasks.matchTasks(stringToFind);
        assert matchedTasks != null : "Even my paintwater has more substance than this 'null' search of yours. Try again, and this time, put some thought into it.";

        return formatTaskList("After sifting through the sands of your endless list, I found these. You owe me:",
                matchedTasks);
    }

}
