package floydai.command;

import java.io.IOException;
import java.util.ArrayList;

import floydai.FloydException;
import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.UI;


/**
 * Command to unmark a task (mark as not done) in the TaskList.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand from user input.
     *
     * @param input user input string, e.g., "unmark 2"
     * @throws FloydException if the input is invalid or cannot parse task number
     */
    public UnmarkCommand(String input) throws FloydException {
        try {
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new FloydException("Please provide a valid task number to unmark.");
        }
    }

    /**
     * Executes the unmark command, marking the task at the specified index as not done.
     *
     * @param tasks   the TaskList containing tasks
     * @param ui      the UI to display feedback
     * @param storage the Storage to save changes
     * @throws FloydException if task index is out of range
     * @throws IOException      if saving to storage fails
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new FloydException("Task number out of range.");
        }
        tasks.unmark(index);
        storage.save(new ArrayList<>(tasks.getAll()));
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
    }
}
