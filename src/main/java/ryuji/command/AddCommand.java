package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.Task;
import ryuji.task.TaskList;
import ryuji.ui.Ui;


/**
 * Represents a command that adds a new task to the task list.
 * This command is responsible for adding a task to the task list, saving it to storage,
 * and displaying a confirmation message to the user.
 */
public class AddCommand extends Command {

    /** The task to be added to the list. */
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task and command keyword.
     *
     * @param command the command keyword indicating the type of task to add
     *                (e.g., "todo", "deadline", "event")
     * @param task    the task to be added to the task list
     */
    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    /**
     * Executes the add command, which involves adding the specified task to the task list,
     * saving the task to storage, and showing a confirmation message to the user.
     *
     * <p>This method delegates the task addition to the {@code TaskList} and
     * instructs the {@code Storage} to save the task to the file system.</p>
     *
     * @param tasks   the current {@code TaskList} containing all tasks
     * @param ui      the {@code Ui} used to display messages to the user
     * @param storage the {@code Storage} used for saving task data to persistent storage
     *
     * @return a confirmation message indicating that the task has been successfully added
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.addToList(task);
        storage.writeTaskToFile(task);
        return message;
    }
}
