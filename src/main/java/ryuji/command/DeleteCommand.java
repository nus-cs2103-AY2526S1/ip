package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command is responsible for removing a task from the task list and updating the persistent storage.
 */
public class DeleteCommand extends Command {

    /** The 1-based position of the task to be deleted. */
    private final int position;

    /**
     * Constructs a {@code DeleteCommand} with the specified command and the position of the task to delete.
     * The position is a 1-based index representing the task's position in the list.
     *
     * @param command  the command keyword (typically "delete")
     * @param position the 1-based position of the task to delete
     * @throws AssertionError if the position is less than or equal to 0
     */
    public DeleteCommand(String command, int position) {
        super(command);
        assert position > 0 : "use a number that is greater than 0";
        this.position = position;
    }

    /**
     * Executes the delete command by removing the task at the specified position from the task list
     * and updating the storage file to reflect the change.
     *
     * <p>If an error occurs during the deletion from storage, a message is returned indicating the failure.</p>
     *
     * @param tasks   the current {@code TaskList} containing all tasks
     * @param ui      the {@code Ui} component used for user interaction
     * @param storage the {@code Storage} component responsible for managing persistent data
     * @return a message indicating the result of the deletion (success or failure)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.deleteFromList(position);
        try {
            storage.removeTaskFromFile(position);
        } catch (Exception e) {
            return "There was an error trying to remove your task master: " + e.getMessage();
        }
        return message;
    }
}
