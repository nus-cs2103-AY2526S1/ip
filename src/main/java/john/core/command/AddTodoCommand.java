package john.core.command;

import john.core.exception.ParseException;
import john.model.Task;
import john.model.TaskList;
import john.model.Todo;
import john.ports.Storage;
import john.ports.Ui;

/**
 * Command that adds a new Todo task to the task list.
 * <p>
 * Responsibilities:
 * - Construct a Todo from a description.
 * - Append it to the provided TaskList.
 * - Persist the updated list via Storage.
 * - Return a user-facing confirmation message in CommandResult.
 * <p>
 * Side effects:
 * - Mutates the given TaskList by adding one task.
 * - Calls storage.save(tasks) to persist state.
 */
public class AddTodoCommand implements Command {
    private final String desc;

    /**
     * Creates an AddTodoCommand.
     *
     * @param desc non-null, non-empty task description
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the command: adds a Todo to the list and saves it.
     *
     * @param tasks   the mutable task list to add into
     * @param storage persistence mechanism used to save the updated list
     * @param ui      user interface used for messaging (not used directly here)
     * @return a success result containing a confirmation message
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = new Todo(desc);
        tasks.add(t);
        storage.save(tasks);
        return CommandResult.ok("Got it. I've added this task:\n" + t +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
