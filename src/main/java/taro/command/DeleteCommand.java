package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Task;
import taro.ui.Ui;

/**
 * Represents the {@code delete} command which removes a task from the current task list.
 * Example:
 *     delete 2
 * removes the second task in the list.
 */
public class DeleteCommand implements Command {
    /** The raw user input string containing the delete command. */
    private final String input;

    /**
     * Constructs a {@code DeleteCommand} with the specified input string.
     *
     * @param input the raw user input that starts with {@code delete}
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the {@code delete} command.
     * Parses the task index from the input string, deletes the task from the
     * {@link TaskList}, saves the updated list to {@link Storage}, and displays
     * a confirmation message through the {@link Ui}.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to save the updated task list
     * @return {@code false} as the program should not terminate after execution
     * @throws TaroException if the input format is invalid or the index is missing
     * @throws NumberFormatException if the index cannot be parsed as an integer
     * @throws IndexOutOfBoundsException if the index does not correspond to an existing task
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            throw new TaroException("Opps!: Usage: delete <index>");
        }
        int idx = Integer.parseInt(parts[1]);

        Task removed = tasks.delete(idx);
        ui.showLine();
        ui.show(" Noted. I've removed this task:");
        ui.show("   " + removed);
        ui.show(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks);
        return false;
    }
}
