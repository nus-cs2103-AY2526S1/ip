package floydai.command;

import java.util.ArrayList;

import floydai.FloydException;
import floydai.storage.Storage;
import floydai.task.Deadline;
import floydai.task.Task;
import floydai.task.TaskList;
import floydai.ui.UI;

/**
 * Command to add a new Deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String input;

    /**
     * Constructs an AddDeadlineCommand with the raw user input.
     *
     * @param input the full user input starting with "deadline"
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command by creating a Deadline task, adding it to the task list,
     * saving the updated list to storage, and showing the added task to the user.
     * Expected format: "deadline (description) /by (due-date)"
     *
     * @param tasks   the TaskList to add the Deadline to
     * @param ui      the UI for interacting with the user
     * @param storage the Storage for persisting tasks
     * @throws FloydException if the input format is invalid or any field is empty
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydException {
        try {
            String[] parts = input.substring(8).trim().split("/by", 2);
            String desc = parts[0].trim();
            String by = parts[1].trim();
            if (desc.isEmpty() || by.isEmpty()) {
                throw new FloydException("Deadline description or time cannot be empty.");
            }
            Task t = new Deadline(desc, by);
            tasks.add(t);
            storage.save(new ArrayList<>(tasks.getAll()));
            ui.showAddTask(t, tasks.size());
        } catch (FloydException e) {
            throw e;
        } catch (Exception e) {
            throw new FloydException("Usage: deadline <description> /by <time>");
        }
    }
}
