package floydai.command;

import java.io.IOException;
import java.util.ArrayList;

import floydai.FloydException;
import floydai.storage.Storage;
import floydai.task.Task;
import floydai.task.TaskList;
import floydai.task.Todo;
import floydai.ui.UI;

/**
 * Command to add a new Todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String input;

    /**
     * Constructs an AddTodoCommand with the raw user input.
     *
     * @param input the full user input starting with "todo"
     */
    public AddTodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command by creating a Todo task, adding it to the task list,
     * saving the updated list to storage, and showing the added task to the user.
     *
     * @param tasks   the TaskList to add the Todo to
     * @param ui      the UI for interacting with the user
     * @param storage the Storage for persisting tasks
     * @throws FloydException if the description is empty
     * @throws IOException      if saving the task list fails
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydException, IOException {
        String desc = input.substring(4).trim();
        if (desc.isEmpty()) {
            throw new FloydException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(desc);
        tasks.add(t);
        storage.save(new ArrayList<>(tasks.getAll()));
        ui.showAddTask(t, tasks.size());
    }
}
